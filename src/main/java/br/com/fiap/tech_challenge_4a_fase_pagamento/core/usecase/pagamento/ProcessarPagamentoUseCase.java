package br.com.fiap.tech_challenge_4a_fase_pagamento.core.usecase.pagamento;

import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.exception.PagamentoJaProcessado;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.PagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public Pagamento execute(Pagamento pagamentoInput){
        Optional<Pagamento> consltaPagamento = pagamentoGateway.consultarStatusPagamento(pagamentoInput.getPedidoId());
        if (consltaPagamento.isPresent()) {
            throw new PagamentoJaProcessado("Pagamento já solicitado: " + pagamentoInput.getPedidoId());
        }
        Optional<Pagamento> pagamento = pagamentoGateway.processarPagamento(pagamentoInput);
        return pagamento.get();
    }

}
