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
     * @param numero O número a ser verificado
     * @return true se o número tiver exatamente 10 caracteres, caso contrário, false
     */
    public boolean verificaSeNumeroPossuiCaracteresNecessarios(BigInteger numero) {
        return numero.toString().length() == 10;
    }

    /**
     * Verifica se a data fornecida é maior que a data atual.
     *
     * @param data A data a ser verificada
     * @return true se a data for maior que a data de hoje, caso contrário, false
     */
    public boolean verificaSeDataMaiorQueHoje(String data) {
        LocalDate dataInformada = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dataInformada.isAfter(LocalDate.now());
    }

    /**
     * Verifica se o valor fornecido é maior que zero.
     *
     * @param valor O valor a ser verificado
     * @return true se o valor for maior que zero, caso contrário, false
     */
    public boolean verificaSeValorMaiorQueZero(BigDecimal valor) {
        return valor.compareTo(BigDecimal.ZERO) > 0;
    }
}
