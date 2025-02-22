package com.desafiojava.agendamentodetransacoes.infra.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa a resposta de um agendamento de transferência financeira.
 * 
 * Esta classe é utilizada para retornar os detalhes de um agendamento
 * após sua criação ou consulta via API REST.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoResponse {

    /**
     * ID do agendamento.
     * 
     * Identificador único do agendamento gerado pelo banco de dados.
     */
    private Long id;

    /**
     * Número da conta de origem da transferência.
     * 
     * A conta de origem é de onde o valor foi retirado para a transferência.
     */
    private BigInteger contaOrigem;

    /**
     * Número da conta de destino da transferência.
     * 
     * A conta de destino é para onde o valor foi enviado.
     */
    private BigInteger contaDestino;

    /**
     * Valor da transferência.
     * 
     * O valor transferido da conta origem para a conta destino.
     */
    private BigDecimal valor;

    /**
     * Taxa aplicada à transferência.
     * 
     * O valor da taxa calculada de acordo com as regras de negócio.
     */
    private BigDecimal taxa;

    /**
     * Data em que a transferência foi agendada.
     * 
     * Representa o dia em que a transferência foi programada para ser realizada.
     */
    private LocalDate dataTransferencia;

    /**
     * Data de agendamento da transferência.
     * 
     * Representa o dia em que o agendamento foi criado no sistema.
     */
    private LocalDate dataAgendamento;

    /**
     * Status do agendamento.
     * 
     * Indica o estado do agendamento (ex.: "AGENDADO", "CONCLUÍDO", etc.).
     */
    private String status;
}
