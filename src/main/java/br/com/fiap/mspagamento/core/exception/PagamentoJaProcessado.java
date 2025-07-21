package br.com.fiap.mspagamento.core.exception;

public class PagamentoJaProcessado extends RuntimeException {
    public PagamentoJaProcessado(String message) {
        super(message);
    }
}