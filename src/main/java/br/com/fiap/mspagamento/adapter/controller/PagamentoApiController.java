package br.com.fiap.mspagamento.adapter.controller;


import br.com.fiap.mspagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.mspagamento.adapter.controller.response.PagamentoResponseDTO;
import br.com.fiap.mspagamento.adapter.mapper.PagamentoMapper;
import br.com.fiap.mspagamento.core.domain.Pagamento;
import br.com.fiap.mspagamento.core.usecase.pagamento.ConsultarPagamentoUseCase;
import br.com.fiap.mspagamento.core.usecase.pagamento.ProcessarPagamentoUseCase;
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
    public ResponseEntity<PagamentoResponseDTO> processarPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        Pagamento pagamentoInput = mapper.toPagamentoDomain(pagamentoRequestDTO);
        Pagamento pagamentoOutPut = processarPagamentoUseCase.execute(pagamentoInput);
        PagamentoResponseDTO pagamentoResponseDTO = mapper.toResponseDTO(pagamentoOutPut);
        return ResponseEntity.ok(pagamentoResponseDTO);
    }

    @GetMapping("/{idPagamento}")
    @Override
    public ResponseEntity<PagamentoResponseDTO> consultarStatus(@PathVariable UUID idPagamento) {
        Pagamento pagamentoOutPut = consultarPagamentoUseCase.execute(idPagamento);
        PagamentoResponseDTO pagamentoResponseDTO = mapper.toResponseDTO(pagamentoOutPut);
        return ResponseEntity.ok(pagamentoResponseDTO);
    }
}
