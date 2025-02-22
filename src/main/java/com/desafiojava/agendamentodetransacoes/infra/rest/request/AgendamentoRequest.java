package com.desafiojava.agendamentodetransacoes.infra.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoRequest {

	private String contaOrigem;
	private String contaDestino;
	private BigDecimal valor;
	private LocalDate dataTransferencia;
}
