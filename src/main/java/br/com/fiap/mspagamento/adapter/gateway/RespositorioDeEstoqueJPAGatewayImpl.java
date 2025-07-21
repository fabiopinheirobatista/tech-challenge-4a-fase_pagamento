package br.com.fiap.mspagamento.adapter.gateway;

import br.com.fiap.mspagamento.adapter.mapper.PagamentoMapper;
import br.com.fiap.mspagamento.adapter.persistence.entity.PagamentoEntity;
import br.com.fiap.mspagamento.adapter.persistence.repository.PagamentoRepository;
import br.com.fiap.mspagamento.core.domain.Pagamento;
import br.com.fiap.mspagamento.core.gateways.PagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RespositorioDeEstoqueJPAGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    @Override
    public Optional<Pagamento> salvarPagamento(Pagamento pagamentoInput) {
        PagamentoEntity pagamentoEntity = pagamentoMapper.toPagamentoEntity(pagamentoInput);
        pagamentoEntity.setDataCriacao(LocalDateTime.now());
        PagamentoEntity pagamentoSalvo = pagamentoRepository.save(pagamentoEntity);
        return Optional.ofNullable(pagamentoSalvo).map(pagamentoMapper::toPagamentoDomain);
    }

    @Override
    public Optional<Pagamento> consultarStatusPagamento(UUID pedidoId) {
        return pagamentoRepository.findFirstByPedidoIdOrderByDataCriacaoDesc(pedidoId)
                .map(pagamentoMapper::toPagamentoDomain);
    }

}
