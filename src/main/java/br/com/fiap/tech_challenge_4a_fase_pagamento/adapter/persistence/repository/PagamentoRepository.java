package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.repository;

import br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {
    Optional<PagamentoEntity> findByPedidoId(UUID pedidoId);
}
