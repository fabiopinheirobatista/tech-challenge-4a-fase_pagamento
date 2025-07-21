package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.exception;

public class ErroSalvarPagamentoException extends  RuntimeException{
    public ErroSalvarPagamentoException(String message) {
        super(message);
    }
}
