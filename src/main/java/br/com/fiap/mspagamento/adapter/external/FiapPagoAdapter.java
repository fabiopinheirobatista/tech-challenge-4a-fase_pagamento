package br.com.fiap.mspagamento.adapter.external;

import br.com.fiap.mspagamento.core.domain.PagamentoGatewayResponse;
import br.com.fiap.mspagamento.core.gateways.ProcessadorPagamentoExternoGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class FiapPagoAdapter implements ProcessadorPagamentoExternoGateway {

    @Override
    public PagamentoGatewayResponse processarPagamento(String numeroCartao, BigDecimal valor, String pedidoId) {
        String cartaoLimpo = numeroCartao.replaceAll("\\s", "");

        if (cartaoLimpo.endsWith("0000")) {
            return PagamentoGatewayResponse.falha("Fundos insuficientes");
        } else if (cartaoLimpo.endsWith("1111")) {
            return PagamentoGatewayResponse.falha("Cartão inválido");
        } else if (cartaoLimpo.endsWith("2222")) {
            return PagamentoGatewayResponse.falha("Cartão expirado");
        } else {
            String transactionId = "FP_" + UUID.randomUUID().toString().substring(0, 8);
            return PagamentoGatewayResponse.sucesso(transactionId);
        }
    }
}

