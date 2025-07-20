package br.com.fiap.tech_challenge_4a_fase_pagamento.core.exception;

public class PagamentoNaoEncontrado extends RuntimeException {
    public PagamentoNaoEncontrado(String message) {
        super(message);
    }
}