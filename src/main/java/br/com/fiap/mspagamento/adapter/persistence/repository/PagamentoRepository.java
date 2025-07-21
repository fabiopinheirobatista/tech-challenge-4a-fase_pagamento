package br.com.fiap.mspagamento.adapter.persistence.repository;

import br.com.fiap.mspagamento.adapter.persistence.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
    Optional<PagamentoEntity> findFirstByPedidoIdOrderByDataCriacaoDesc(UUID pedidoId);
}
