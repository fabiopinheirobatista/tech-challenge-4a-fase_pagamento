package br.com.fiap.mspagamento.adapter.mapper;

import br.com.fiap.mspagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.mspagamento.adapter.controller.response.PagamentoResponseDTO;
import br.com.fiap.mspagamento.adapter.persistence.entity.PagamentoEntity;
import br.com.fiap.mspagamento.adapter.persistence.entity.StatusPagamentoEntity;
import br.com.fiap.mspagamento.core.domain.Pagamento;
import br.com.fiap.mspagamento.core.domain.StatusPagamento;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PagamentoMapper {

    public Pagamento toPagamentoDomain(PagamentoRequestDTO dto) {
        if (dto == null) return null;
        return Pagamento.builder()
                .pedidoId(dto.pedidoId())
                .valor(dto.valor())
                .numeroCartao(dto.numeroCartao())
                .status(StatusPagamento.PROCESSANDO)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        if (pagamento == null) return null;
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getValor(),
                pagamento.getNumeroCartao(),
                pagamento.getStatus().name(),
                pagamento.getDataCriacao(),
                pagamento.getIdTransacao()
        );
    }

    public PagamentoEntity toPagamentoEntity(Pagamento pagamento) {
        if (pagamento == null) return null;
        PagamentoEntity entity = new PagamentoEntity();
        entity.setId(pagamento.getId());
        entity.setPedidoId(pagamento.getPedidoId());
        entity.setValor(pagamento.getValor());
        entity.setNumeroCartao(pagamento.getNumeroCartao());
        entity.setStatus(
            pagamento.getStatus() != null ?
                StatusPagamentoEntity.valueOf(pagamento.getStatus().name()) : null
        );
        entity.setDataCriacao(pagamento.getDataCriacao());
        entity.setIdTransacao(pagamento.getIdTransacao());
        return entity;
    }

    public Pagamento toPagamentoDomain(PagamentoEntity entity) {
        if (entity == null) return null;
        return Pagamento.builder()
                .id(entity.getId())
                .pedidoId(entity.getPedidoId())
                .valor(entity.getValor())
                .numeroCartao(entity.getNumeroCartao())
                .status(
                    entity.getStatus() != null ?
                        StatusPagamento.valueOf(entity.getStatus().name()) : null
                )
                .dataCriacao(entity.getDataCriacao())
                .idTransacao(entity.getIdTransacao())
                .build();
    }
}
