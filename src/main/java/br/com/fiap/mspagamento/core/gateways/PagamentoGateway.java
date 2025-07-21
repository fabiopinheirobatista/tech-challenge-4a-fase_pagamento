package br.com.fiap.mspagamento.core.gateways;

import br.com.fiap.mspagamento.core.domain.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoGateway {

    Optional<Pagamento> processarPagamento(Pagamento pagamentoInput);
    Optional<Pagamento> consultarStatusPagamento(UUID pedidoId);
}
