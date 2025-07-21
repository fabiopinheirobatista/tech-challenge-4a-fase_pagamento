package br.com.fiap.mspagamento.core.domain;

import lombok.Getter;

@Getter
public class PagamentoGatewayResponse {
    private final boolean sucesso;
    private final String mensagem;
    private final String idTransacao;

    private PagamentoGatewayResponse(boolean sucesso, String mensagem, String idTransacao) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.idTransacao = idTransacao;
    }

    public static PagamentoGatewayResponse sucesso(String idTransacao) {
        return new PagamentoGatewayResponse(true, "Pagamento aprovado", idTransacao);
    }

    public static PagamentoGatewayResponse falha(String mensagem) {
        return new PagamentoGatewayResponse(false, mensagem, null);
    }
}
