package com.desafiojava.agendamentodetransacoes.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "conta_origem", nullable = false, length = 10)
	    private String contaOrigem;

	    @Column(name = "conta_destino", nullable = false, length = 10)
	    private String contaDestino;

	    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
	    private BigDecimal valor;

	    @Column(name = "taxa", nullable = false, precision = 10, scale = 2)
	    private BigDecimal taxa;

	    @Column(name = "data_transferencia", nullable = false)
	    private LocalDate dataTransferencia;

	    @Column(name = "data_agendamento", nullable = false)
	    private LocalDate dataAgendamento;

	    @Column(name = "status", nullable = false, length = 20)
	    private String status;
}
