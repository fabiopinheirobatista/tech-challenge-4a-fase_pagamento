package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller;


import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.response.PagamentoResponseDTO;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.mapper.PagamentoMapper;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.PagamentoGateway;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.usecase.pagamento.ConsultarPagamentoUseCase;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.usecase.pagamento.ProcessarPagamentoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@AllArgsConstructor
public class PagamentoApiController implements PagamentoController{

    private final ConsultarPagamentoUseCase consultarPagamentoUseCase;
    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final PagamentoMapper mapper;

    @PostMapping
    @Override
    public ResponseEntity<PagamentoResponseDTO> processarPagamento(PagamentoRequestDTO pagamentoRequestDTO) throws Exception {
        Pagamento pagamentoInput = mapper.toPagamentoDomain(pagamentoRequestDTO);
        Pagamento pagamentoOutPut = processarPagamentoUseCase.execute(pagamentoInput);
        PagamentoResponseDTO pagamentoResponseDTO = mapper.toResponseDTO(pagamentoOutPut);
        return ResponseEntity.ok(pagamentoResponseDTO);
    }

    @GetMapping("/{idPagamento}")
    @Override
    public ResponseEntity<PagamentoResponseDTO> consultarStatus(UUID pedidoId) {
        Pagamento pagamentoOutPut = consultarPagamentoUseCase.execute(pedidoId);
        PagamentoResponseDTO pagamentoResponseDTO = mapper.toResponseDTO(pagamentoOutPut);
        return ResponseEntity.ok(pagamentoResponseDTO);
    }
}
