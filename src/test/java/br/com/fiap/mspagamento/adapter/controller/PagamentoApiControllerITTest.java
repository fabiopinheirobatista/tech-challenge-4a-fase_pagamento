package br.com.fiap.mspagamento.adapter.controller;

import br.com.fiap.mspagamento.TechChallenge4aFasePagamentoApplication;
import br.com.fiap.mspagamento.adapter.controller.request.PagamentoRequestDTO;
import br.com.fiap.mspagamento.adapter.persistence.entity.PagamentoEntity;
import br.com.fiap.mspagamento.adapter.persistence.entity.StatusPagamentoEntity;
import br.com.fiap.mspagamento.adapter.persistence.repository.PagamentoRepository;
import br.com.fiap.mspagamento.core.gateways.PagamentoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallenge4aFasePagamentoApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
@DisplayName("Testes de Integração - PagamentoApiController")
class PagamentoApiControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PagamentoRepository pagamentoRepository;

    @Test
    @DisplayName("Deve realizar pagamento com sucesso")
    void pagamentoComSucesso() throws Exception {

        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO(UUID.randomUUID(), "1234567890123456", new BigDecimal(100.00));

        when(pagamentoRepository.save(any(PagamentoEntity.class)))
                .thenAnswer(invocation -> {
                    PagamentoEntity entity = invocation.getArgument(0);
                    return PagamentoEntity.builder()
                            .id(1L)
                            .pedidoId(entity.getPedidoId())
                            .numeroCartao(entity.getNumeroCartao())
                            .valor(entity.getValor())
                            .status(StatusPagamentoEntity.PROCESSANDO)
                            .dataCriacao(LocalDateTime.now())
                            .build();
                });

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamentoRequestDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve realizar consulta com sucesso")
    void consultaPagamentoComSucesso() throws Exception {

        UUID pedidoId = UUID.randomUUID();

        PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
                .id(1L)
                .pedidoId(pedidoId)
                .numeroCartao("1234567890123456")
                .valor(new BigDecimal("100.00"))
                .status(StatusPagamentoEntity.PROCESSANDO)
                .dataCriacao(LocalDateTime.now())
                .build();

        when(pagamentoRepository.findFirstByPedidoIdOrderByDataCriacaoDesc(pedidoId))
                .thenReturn(Optional.of(pagamentoEntity));

        mockMvc.perform(get("/pagamentos/{pedidoId}", pedidoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}