package com.desafiojava.agendamentodetransacoes.domain;

import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;
import com.desafiojava.agendamentodetransacoes.infra.database.AgendamentoRepository;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoDomain {

	private final AgendamentoRepository agendamentoRepository;

	/**
	 * Agenda uma transferência financeira com base nas regras de negócio.
	 *
	 * @param request Objeto contendo os dados da transação
	 * @return AgendamentoResponse com os detalhes do agendamento
	 */
	public AgendamentoResponse agendarTransacao(AgendamentoRequest request) {
		LocalDate dataAgendamento = LocalDate.now();
		LocalDate dataTransferencia = request.getDataTransferencia();

		long diasDiferenca = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);
		BigDecimal taxa = calcularTaxa(request.getValor(), diasDiferenca);

		if (taxa == null) {
			throw new IllegalArgumentException("Nenhuma taxa aplicável. Transferência não permitida.");
		}

		Agendamento agendamento = Agendamento.builder().contaOrigem(request.getContaOrigem())
				.contaDestino(request.getContaDestino()).valor(request.getValor()).taxa(taxa)
				.dataTransferencia(dataTransferencia).dataAgendamento(dataAgendamento).status("AGENDADO").build();

		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

		return AgendamentoResponse.builder().id(agendamentoSalvo.getId()).contaOrigem(agendamentoSalvo.getContaOrigem())
				.contaDestino(agendamentoSalvo.getContaDestino()).valor(agendamentoSalvo.getValor())
				.taxa(agendamentoSalvo.getTaxa()).dataTransferencia(agendamentoSalvo.getDataTransferencia())
				.dataAgendamento(agendamentoSalvo.getDataAgendamento()).status(agendamentoSalvo.getStatus()).build();
	}

	/**
	 * Calcula a taxa da transferência com base na tabela de regras de taxa.
	 *
	 * @param valor         Valor da transferência
	 * @param diasDiferenca Número de dias entre o agendamento e a transferência
	 * @return Valor da taxa ou null se nenhuma taxa for aplicável
	 */
	private BigDecimal calcularTaxa(BigDecimal valor, long diasDiferenca) {
		if (diasDiferenca == 0) {
			return BigDecimal.valueOf(3.00).add(valor.multiply(BigDecimal.valueOf(0.025)));
		} else if (diasDiferenca >= 1 && diasDiferenca <= 10) {
			return BigDecimal.valueOf(12.00);
		} else if (diasDiferenca >= 11 && diasDiferenca <= 20) {
			return valor.multiply(BigDecimal.valueOf(0.082));
		} else if (diasDiferenca >= 21 && diasDiferenca <= 30) {
			return valor.multiply(BigDecimal.valueOf(0.069));
		} else if (diasDiferenca >= 31 && diasDiferenca <= 40) {
			return valor.multiply(BigDecimal.valueOf(0.047));
		} else if (diasDiferenca >= 41 && diasDiferenca <= 50) {
			return valor.multiply(BigDecimal.valueOf(0.017));
		}
		return null; // Nenhuma taxa aplicável
	}

	/**
	 * Retorna lista de agendamentos.
	 * @return List<Agendamento> do pacote com.desafiojava.agendamentodetransacoes.domain.model.Agendamento
	 */
	public List<Agendamento> listarAgendamento() {
		return agendamentoRepository.findAll();
	}
}
