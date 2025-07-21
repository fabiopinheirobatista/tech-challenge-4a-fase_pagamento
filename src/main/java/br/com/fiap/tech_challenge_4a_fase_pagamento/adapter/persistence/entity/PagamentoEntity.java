package br.com.fiap.tech_challenge_4a_fase_pagamento.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_pagamentos_pedidoId", columnNames = {"pedido_id"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pedido_id", columnDefinition = "BINARY(16)", unique = true)
    private UUID pedidoId;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String numeroCartao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamentoEntity status;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;
}