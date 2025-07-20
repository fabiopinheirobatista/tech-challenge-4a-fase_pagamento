package br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways;

import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoGateway {

    Optional<Pagamento> processarPagamento(Pagamento pagamentoInput);
    Optional<Pagamento> consultarStatusPagamento(UUID pedidoId);
}
