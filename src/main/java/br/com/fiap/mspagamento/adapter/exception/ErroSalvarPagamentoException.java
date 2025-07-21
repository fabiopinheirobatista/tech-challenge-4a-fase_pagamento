package br.com.fiap.mspagamento.adapter.exception;

public class ErroSalvarPagamentoException extends  RuntimeException{
    public ErroSalvarPagamentoException(String message) {
        super(message);
    }
}
