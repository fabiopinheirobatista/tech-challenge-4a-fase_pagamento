package br.com.fiap.mspagamento.core.exception;

public class PagamentoNaoEncontrado extends RuntimeException {
    public PagamentoNaoEncontrado(String message) {
        super(message);
    }
}