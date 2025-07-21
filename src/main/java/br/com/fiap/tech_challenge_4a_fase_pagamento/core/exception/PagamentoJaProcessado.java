package br.com.fiap.tech_challenge_4a_fase_pagamento.core.exception;

public class PagamentoJaProcessado extends RuntimeException {
    public PagamentoJaProcessado(String message) {
        super(message);
    }
}