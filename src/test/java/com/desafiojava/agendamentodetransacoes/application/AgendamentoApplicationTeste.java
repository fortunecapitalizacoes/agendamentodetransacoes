package com.desafiojava.agendamentodetransacoes.application;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgendamentoApplicationTest {

    private AgendamentoApplication agendamentoApplication;

    @BeforeEach
    void setUp() {
        agendamentoApplication = new AgendamentoApplication();
    }

    @Test
    void testVerificaSeNumeroPossuiCaracteresNecessarios() {
        assertTrue(agendamentoApplication.verificaSeNumeroPossuiCaracteresNecessarios(new BigInteger("1234567890")));
        assertFalse(agendamentoApplication.verificaSeNumeroPossuiCaracteresNecessarios(new BigInteger("12345")));
    }

    @Test
    void testVerificaSeDataMaiorQueHoje() {
        String dataFutura = LocalDate.now().plusDays(1).toString();
        String dataPassada = LocalDate.now().minusDays(1).toString();
        
        assertTrue(agendamentoApplication.verificaSeDataMaiorQueHoje(dataFutura));
        assertFalse(agendamentoApplication.verificaSeDataMaiorQueHoje(dataPassada));
    }

    @Test
    void testVerificaSeValorMaiorQueZero() {
        assertTrue(agendamentoApplication.verificaSeValorMaiorQueZero(new BigDecimal("10.50")));
        assertFalse(agendamentoApplication.verificaSeValorMaiorQueZero(new BigDecimal("0.00")));
        assertFalse(agendamentoApplication.verificaSeValorMaiorQueZero(new BigDecimal("-5.75")));
    }

    @Test
    void testVerificaSeDataCancelamentoEstaAntesDaTransferencia() {
        String dataCancelamento = LocalDate.now().toString();
        String dataAgendamento = LocalDate.now().plusDays(5).toString();
        String dataAposAgendamento = LocalDate.now().plusDays(10).toString();
        
        assertTrue(agendamentoApplication.verificaSeDataCancelamentoEstaAntesDaTransferencia(dataCancelamento, dataAgendamento));
        assertFalse(agendamentoApplication.verificaSeDataCancelamentoEstaAntesDaTransferencia(dataAposAgendamento, dataAgendamento));
    }
}
