package br.com.fiap.mspagamento.adapter.controller;

import br.com.fiap.mspagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.mspagamento.adapter.controller.response.PagamentoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface PagamentoController {
    ResponseEntity<PagamentoResponseDTO> processarPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO);
    ResponseEntity<PagamentoResponseDTO> consultarStatus(@PathVariable UUID pedidoId);

}
