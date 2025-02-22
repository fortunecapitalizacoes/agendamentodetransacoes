package com.desafiojava.agendamentodetransacoes.domain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.desafiojava.agendamentodetransacoes.application.AgendamentoApplication;
import com.desafiojava.agendamentodetransacoes.application.enuns.FluxoContaEnum;
import com.desafiojava.agendamentodetransacoes.application.enuns.StatusEnum;
import com.desafiojava.agendamentodetransacoes.application.exceptions.*;
import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;
import com.desafiojava.agendamentodetransacoes.infra.database.AgendamentoRepository;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AgendamentoDomainTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private AgendamentoApplication application;

    @InjectMocks
    private AgendamentoDomain agendamentoDomain;

    private AgendamentoRequest request;

    @BeforeEach
    void setUp() {
        request = new AgendamentoRequest();
        request.setContaOrigem(new BigInteger("1234567890"));
        request.setContaDestino(new BigInteger("0987654321"));
        request.setValor(new BigDecimal("1000"));
        request.setDataTransferencia(LocalDate.now().plusDays(5));
    }

    @Test
    void deveAgendarTransacaoComSucesso() {
        when(application.verificaSeNumeroPossuiCaracteresNecessarios(any())).thenReturn(true);
        when(application.verificaSeValorMaiorQueZero(any())).thenReturn(true);
        when(agendamentoRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        AgendamentoResponse response = agendamentoDomain.agendarTransacao(request);

        assertNotNull(response);
        assertEquals(StatusEnum.AGENDADO, response.getStatus());
    }

    @Test
    void deveLancarExcecaoQuandoNumeroContaInvalido() {
        when(application.verificaSeNumeroPossuiCaracteresNecessarios(any())).thenReturn(false);

        assertThrows(NumeroInvalidoException.class, () -> agendamentoDomain.agendarTransacao(request));
    }

    @Test
    void deveLancarExcecaoQuandoValorTransacaoInvalido() {
        when(application.verificaSeNumeroPossuiCaracteresNecessarios(any())).thenReturn(true);
        when(application.verificaSeValorMaiorQueZero(any())).thenReturn(false);

        assertThrows(ValorTransacaoInvalidoException.class, () -> agendamentoDomain.agendarTransacao(request));
    }

   
    @Test
    void deveCancelarAgendamentoComSucesso() {
        Agendamento agendamento = new Agendamento();
        agendamento.setStatus(StatusEnum.AGENDADO);
        agendamento.setDataTransferencia(LocalDate.now().plusDays(2));

        when(agendamentoRepository.findById(anyLong())).thenReturn(Optional.of(agendamento));
        when(application.verificaSeDataCancelamentoEstaAntesDaTransferencia(any(), any())).thenReturn(true);
        when(agendamentoRepository.save(any())).thenReturn(agendamento);

        Agendamento resultado = agendamentoDomain.cancelarAgendamento(1L);

        assertNotNull(resultado);
        assertEquals(StatusEnum.CANCELADO, resultado.getStatus());
    }

    @Test
    void deveLancarExcecaoQuandoDataCancelamentoInvalida() {
        Agendamento agendamento = new Agendamento();
        agendamento.setDataTransferencia(LocalDate.now().minusDays(1));

        when(agendamentoRepository.findById(anyLong())).thenReturn(Optional.of(agendamento));
        when(application.verificaSeDataCancelamentoEstaAntesDaTransferencia(any(), any())).thenReturn(false);

        assertThrows(DataCancelamentoInvalidoException.class, () -> agendamentoDomain.cancelarAgendamento(1L));
    }
}
