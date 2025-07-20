package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.mapper;

import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.controller.response.PagamentoResponseDTO;
import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity.PagamentoEntity;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.Pagamento;
import br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.StatusPagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toPagamentoDomain(PagamentoRequestDTO dto) {
        if (dto == null) return null;
        return Pagamento.builder()
                .pedidoId(dto.pedidoId())
                .valor(dto.valor())
                .build();
    }

    public PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        if (pagamento == null) return null;
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getValor(),
                pagamento.getStatus().name(),
                pagamento.getDataCriacao()
        );
    }

    public PagamentoEntity toPagamentoEntity(Pagamento pagamento) {
        if (pagamento == null) return null;
        PagamentoEntity entity = new PagamentoEntity();
        entity.setId(pagamento.getId());
        entity.setPedidoId(pagamento.getPedidoId());
        entity.setValor(pagamento.getValor());
        entity.setStatus(
            pagamento.getStatus() != null ?
                br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity.StatusPagamentoEntity.valueOf(pagamento.getStatus().name()) : null
        );
        entity.setDataCriacao(pagamento.getDataCriacao());
        return entity;
    }

    public Pagamento toPagamentoDomain(PagamentoEntity entity) {
        if (entity == null) return null;
        return Pagamento.builder()
                .id(entity.getId())
                .pedidoId(entity.getPedidoId())
                .valor(entity.getValor())
                .status(
                    entity.getStatus() != null ?
                        br.com.fiap.tech_challenge_4a_fase_pagamento.core.domain.StatusPagamento.valueOf(entity.getStatus().name()) : null
                )
                .dataCriacao(entity.getDataCriacao())
                .build();
    }
}
