package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private UUID pedidoId;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamentoEntity status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;
}