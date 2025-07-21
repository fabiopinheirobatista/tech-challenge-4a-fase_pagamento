package br.com.fiap.mspagamento.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Pagamento {

    @EqualsAndHashCode.Include
    private Long id;
    private UUID pedidoId;
    private BigDecimal valor;
    private String numeroCartao;
    private StatusPagamento status;
    private LocalDateTime dataCriacao;

}
