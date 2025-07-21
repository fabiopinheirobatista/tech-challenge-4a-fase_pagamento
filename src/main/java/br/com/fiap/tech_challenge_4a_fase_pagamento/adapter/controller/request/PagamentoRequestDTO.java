package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.request;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDTO(UUID pedidoId,
                                  String numeroCartao,
                                  BigDecimal valor) {
}
