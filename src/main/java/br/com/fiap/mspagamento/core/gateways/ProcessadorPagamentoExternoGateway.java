package br.com.fiap.mspagamento.core.gateways;

import br.com.fiap.mspagamento.core.domain.PagamentoGatewayResponse;

import java.math.BigDecimal;

public interface ProcessadorPagamentoExternoGateway {
    PagamentoGatewayResponse processarPagamento(String numeroCartao, BigDecimal valor, String pedidoId);
}
