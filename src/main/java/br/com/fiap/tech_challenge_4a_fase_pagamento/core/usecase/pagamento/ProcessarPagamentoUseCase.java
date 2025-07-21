package br.com.fiap.tech_challenge_4a_fase_pagamento.core.usecase.pagamento;

import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.exception.ErroSalvarPagamentoException;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.PagamentoGatewayResponse;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.StatusPagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.exception.PagamentoJaProcessado;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.PagamentoGateway;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.ProcessadorPagamentoExternoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcessarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final ProcessadorPagamentoExternoGateway processadorPagamentoGateway;

    public Pagamento execute(Pagamento pagamentoInput) {

        Optional<Pagamento> consultaPagamento = pagamentoGateway.consultarStatusPagamento(pagamentoInput.getPedidoId());
        if (consultaPagamento.isPresent()) {
            throw new PagamentoJaProcessado("Pagamento já solicitado: " + pagamentoInput.getPedidoId());
        }

            PagamentoGatewayResponse respostaPagamento = processadorPagamentoGateway.processarPagamento(
            pagamentoInput.getNumeroCartao(),
            pagamentoInput.getValor(),
            pagamentoInput.getPedidoId().toString()
        );

        pagamentoInput.setStatus(respostaPagamento.isSucesso() ? StatusPagamento.APROVADO : StatusPagamento.RECUSADO);

        Optional<Pagamento> pagamentoSalvo = pagamentoGateway.processarPagamento(pagamentoInput);
        return pagamentoSalvo.orElseThrow(() -> new ErroSalvarPagamentoException("Erro ao salvar pagamento"));
    }

}
