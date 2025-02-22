package com.desafiojava.agendamentodetransacoes.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * Representa um agendamento de transferência financeira no sistema.
 * 
 * A classe contém informações sobre a conta de origem, a conta de destino,
 * o valor da transferência, a taxa aplicada, as datas de agendamento e de 
 * transferência, além do status do agendamento.
 */
@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    /**
     * Identificador único do agendamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número da conta de origem da transferência.
     * Deve ter exatamente 10 dígitos.
     */
    @Column(name = "conta_origem", nullable = false, length = 10)
    private BigInteger contaOrigem;

    /**
     * Número da conta de destino da transferência.
     * Deve ter exatamente 10 dígitos.
     */
    @Column(name = "conta_destino", nullable = false, length = 10)
    private BigInteger contaDestino;

    /**
     * Valor da transferência.
     * Deve ser um número com até 10 dígitos e 2 casas decimais.
     */
    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    /**
     * Taxa aplicada à transferência.
     * Deve ser um número com até 10 dígitos e 2 casas decimais.
     */
    @Column(name = "taxa", nullable = false, precision = 10, scale = 2)
    private BigDecimal taxa;

    /**
     * Data prevista para a transferência.
     */
    @Column(name = "data_transferencia", nullable = false)
    private LocalDate dataTransferencia;

    /**
     * Data do agendamento da transferência.
     */
    @Column(name = "data_agendamento", nullable = false)
    private LocalDate dataAgendamento;

    /**
     * Status do agendamento da transferência.
     * Pode ser "AGENDADO", "EXECUTADO", entre outros status possíveis.
     */
    @Column(name = "status", nullable = false, length = 20)
    private String status;
}
