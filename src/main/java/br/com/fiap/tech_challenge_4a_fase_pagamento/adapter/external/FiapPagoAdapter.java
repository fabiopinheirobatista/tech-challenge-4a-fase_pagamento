package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.external;

import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.PagamentoGatewayResponse;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.ProcessadorPagamentoExternoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class FiapPagoAdapter implements ProcessadorPagamentoExternoGateway {
    private static final Logger logger = LoggerFactory.getLogger(FiapPagoAdapter.class);

    @Override
    public PagamentoGatewayResponse processarPagamento(String numeroCartao, BigDecimal valor, String pedidoId) {
        logger.info("Processando pagamento através do FiapPago mock para o pedido: {}", pedidoId);
        String cartaoLimpo = numeroCartao.replaceAll("\\s", "");

        if (cartaoLimpo.endsWith("0000")) {
            logger.info("Mock: Pagamento negado - fundos insuficientes para o pedido: {}", pedidoId);
            return PagamentoGatewayResponse.falha("Fundos insuficientes");
        } else if (cartaoLimpo.endsWith("1111")) {
            logger.info("Mock: Pagamento negado - cartão inválido para o pedido: {}", pedidoId);
            return PagamentoGatewayResponse.falha("Cartão inválido");
        } else if (cartaoLimpo.endsWith("2222")) {
            logger.info("Mock: Pagamento negado - cartão expirado para o pedido: {}", pedidoId);
            return PagamentoGatewayResponse.falha("Cartão expirado");
        } else {
            String transactionId = "MP_" + UUID.randomUUID().toString().substring(0, 8);
            logger.info("Mock: Pagamento aprovado para o pedido: {} com transação: {}", pedidoId, transactionId);
            return PagamentoGatewayResponse.sucesso(transactionId);
        }
    }
}

