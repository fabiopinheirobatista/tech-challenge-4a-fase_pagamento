package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.gateway;

import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.mapper.PagamentoMapper;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity.PagamentoEntity;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.repository.PagamentoRepository;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.gateways.PagamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RespositorioDeEstoqueJPAGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    @Override
    public Optional<Pagamento> processarPagamento(Pagamento pagamentoInput) {
        PagamentoEntity pagamentoEntity = pagamentoMapper.toPagamentoEntity(pagamentoInput);
        PagamentoEntity pagamentoSalvo = pagamentoRepository.save(pagamentoEntity);
        return Optional.ofNullable(pagamentoSalvo).map(pagamentoMapper::toPagamentoDomain);
    }

    @Override
    public Optional<Pagamento> consultarStatusPagamento(UUID pedidoId) {
        return pagamentoRepository.findByPedidoId(pedidoId)
                .map(pagamentoMapper::toPagamentoDomain);
    }

}
