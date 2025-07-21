package br.com.fiap.mspagamento.core.usecase.pagamento;

import br.com.fiap.mspagamento.core.domain.Pagamento;
import br.com.fiap.mspagamento.core.exception.PagamentoNaoEncontrado;
import br.com.fiap.mspagamento.core.gateways.PagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsultarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public Pagamento execute(UUID pedidoId){
        Optional<Pagamento> pagamento = pagamentoGateway.consultarStatusPagamento(pedidoId);
        return pagamento.orElseThrow(() -> new PagamentoNaoEncontrado("Pagamento não encontrado para o pedido: " + pedidoId));
    }
}
