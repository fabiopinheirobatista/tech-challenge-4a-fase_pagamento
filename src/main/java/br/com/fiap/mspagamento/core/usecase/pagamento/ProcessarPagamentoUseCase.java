package br.com.fiap.mspagamento.core.usecase.pagamento;

import br.com.fiap.mspagamento.adapter.exception.ErroSalvarPagamentoException;
import br.com.fiap.mspagamento.core.domain.Pagamento;
import br.com.fiap.mspagamento.core.domain.PagamentoGatewayResponse;
import br.com.fiap.mspagamento.core.domain.StatusPagamento;
import br.com.fiap.mspagamento.core.exception.PagamentoJaProcessado;
import br.com.fiap.mspagamento.core.gateways.PagamentoGateway;
import br.com.fiap.mspagamento.core.gateways.ProcessadorPagamentoExternoGateway;
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
