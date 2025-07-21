package br.com.fiap.mspagamento.core.exception;

public class PagamentoJaProcessadoException extends RuntimeException {
    public PagamentoJaProcessadoException(String message) {
        super(message);
    }
}