package com.desafiojava.agendamentodetransacoes.infra.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa o pedido de agendamento de uma transferência financeira.
 * 
 * Esta classe é utilizada para capturar os dados da requisição de agendamento
 * enviados pelo cliente (via API REST).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoRequest {

    /**
     * Número da conta de origem da transferência.
     * 
     * A conta de origem é de onde o valor será retirado para a transferência.
     */
    private BigInteger contaOrigem;

    /**
     * Número da conta de destino da transferência.
     * 
     * A conta de destino é para onde o valor será enviado.
     */
    private BigInteger contaDestino;

    /**
     * Valor da transferência.
     * 
     * O valor que será transferido da conta origem para a conta destino.
     */
    private BigDecimal valor;

    /**
     * Data em que a transferência deve ocorrer.
     * 
     * Representa o dia em que a transferência será processada.
     */
    private LocalDate dataTransferencia;
}
