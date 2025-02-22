package com.desafiojava.agendamentodetransacoes.application;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class AgendamentoApplication {
	
    /**
     * Verifica se o número fornecido possui exatamente 10 caracteres.
     *
     * @param numero O número a ser verificado.
     * @return {@code true} se o número tiver exatamente 10 caracteres, 
     *         caso contrário, {@code false}.
     */
    public boolean verificaSeNumeroPossuiCaracteresNecessarios(BigInteger numero) {
        return numero.toString().length() == 10;
    }

    /**
     * Verifica se a data fornecida é maior que a data atual.
     *
     * @param data A data a ser verificada no formato {@code yyyy-MM-dd}.
     * @return {@code true} se a data for maior que a data de hoje, 
     *         caso contrário, {@code false}.
     */
    public boolean verificaSeDataMaiorQueHoje(String data) {
        LocalDate dataInformada = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dataInformada.isAfter(LocalDate.now());
    }

    /**
     * Verifica se o valor fornecido é maior que zero.
     *
     * @param valor O valor a ser verificado.
     * @return {@code true} se o valor for maior que zero, 
     *         caso contrário, {@code false}.
     */
    public boolean verificaSeValorMaiorQueZero(BigDecimal valor) {
        return valor.compareTo(BigDecimal.ZERO) > 0;
    }
    
    /**
     * Verifica se a data de cancelamento ocorre antes da data do agendamento.
     *
     * @param cancelamento A data de cancelamento no formato {@code yyyy-MM-dd}.
     * @param agendamento A data do agendamento no formato {@code yyyy-MM-dd}.
     * @return {@code true} se a data de cancelamento for anterior à data de agendamento, 
     *         caso contrário, {@code false}.
     */
    public boolean verificaSeDataCancelamentoEstaAntesDaTransferencia(String cancelamento, String agendamento) {
        LocalDate dataCancelamento = LocalDate.parse(cancelamento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataAgendada = LocalDate.parse(agendamento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dataCancelamento.isBefore(dataAgendada);
    }
}
