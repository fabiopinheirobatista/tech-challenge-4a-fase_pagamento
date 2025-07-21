package br.com.fiap.mspagamento.adapter.controller.request;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDTO(UUID pedidoId,
                                  String numeroCartao,
                                  BigDecimal valor) {
}
