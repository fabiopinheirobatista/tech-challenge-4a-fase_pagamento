package br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways;

import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.PagamentoGatewayResponse;

import java.math.BigDecimal;

public interface ProcessadorPagamentoExternoGateway {
    PagamentoGatewayResponse processarPagamento(String numeroCartao, BigDecimal valor, String pedidoId);
}
