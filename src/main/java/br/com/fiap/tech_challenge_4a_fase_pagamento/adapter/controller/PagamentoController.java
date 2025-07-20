package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller;

import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.response.PagamentoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface PagamentoController {
    ResponseEntity<PagamentoResponseDTO> processarPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) throws Exception;
    ResponseEntity<PagamentoResponseDTO> consultarStatus(@PathVariable UUID pedidoId);

}
