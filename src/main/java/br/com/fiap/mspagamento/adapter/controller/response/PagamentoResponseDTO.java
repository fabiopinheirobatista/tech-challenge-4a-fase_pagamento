package br.com.fiap.mspagamento.adapter.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponseDTO(Long id,
                                   UUID pedidoId,
                                   BigDecimal valor,
                                   String numeroCartao,
                                   String status,
                                   LocalDateTime dataCriacao) {
}
