package com.desafiojava.agendamentodetransacoes.domain;

import com.desafiojava.agendamentodetransacoes.application.AgendamentoApplication;
import com.desafiojava.agendamentodetransacoes.application.FluxoContaEnum;
import com.desafiojava.agendamentodetransacoes.application.exceptions.DataInvalidoException;
import com.desafiojava.agendamentodetransacoes.application.exceptions.NumeroInvalidoException;
import com.desafiojava.agendamentodetransacoes.application.exceptions.ValorTransacaoInvalidoException;
import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;
import com.desafiojava.agendamentodetransacoes.infra.database.AgendamentoRepository;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pela lógica de agendamento de transações financeiras.
 * Inclui validações de dados e cálculo de taxas.
 */
@Component
@RequiredArgsConstructor
public class AgendamentoDomain {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoApplication application;

    /**
     * Agenda uma transferência financeira com base nas regras de negócio.
     *
     * @param request Objeto contendo os dados da transação.
     * @return AgendamentoResponse com os detalhes do agendamento.
     * @throws NumeroInvalidoException Se o número da conta for inválido.
     * @throws DataInvalidoException Se a data de transferência for inválida.
     * @throws ValorTransacaoInvalidoException Se o valor da transação for inválido.
     */
    public AgendamentoResponse agendarTransacao(AgendamentoRequest request) {
        validaNumerodaConta(request.getContaOrigem(), FluxoContaEnum.ORIGEM);
        validaNumerodaConta(request.getContaDestino(), FluxoContaEnum.DESTINO);
        validaDataTransferencia(request.getDataTransferencia());
        validaValorTransferencia(request.getValor());

        LocalDate dataAgendamento = LocalDate.now();
        LocalDate dataTransferencia = request.getDataTransferencia();
        long diasDiferenca = ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);

        BigDecimal taxa = calcularTaxa(request.getValor(), diasDiferenca)
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma taxa aplicável. Transferência não permitida."));

        Agendamento agendamento = Agendamento.builder()
                .contaOrigem(request.getContaOrigem())
                .contaDestino(request.getContaDestino())
                .valor(request.getValor())
                .taxa(taxa)
                .dataTransferencia(dataTransferencia)
                .dataAgendamento(dataAgendamento)
                .status("AGENDADO")
                .build();

        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        return AgendamentoResponse.builder()
                .id(agendamentoSalvo.getId())
                .contaOrigem(agendamentoSalvo.getContaOrigem())
                .contaDestino(agendamentoSalvo.getContaDestino())
                .valor(agendamentoSalvo.getValor())
                .taxa(agendamentoSalvo.getTaxa())
                .dataTransferencia(agendamentoSalvo.getDataTransferencia())
                .dataAgendamento(agendamentoSalvo.getDataAgendamento())
                .status(agendamentoSalvo.getStatus())
                .build();
    }

    /**
     * Calcula a taxa da transferência com base na tabela de regras de taxa.
     *
     * @param valor         Valor da transferência.
     * @param diasDiferenca Número de dias entre o agendamento e a transferência.
     * @return Valor da taxa ou Optional.empty() se nenhuma taxa for aplicável.
     */
    private Optional<BigDecimal> calcularTaxa(BigDecimal valor, long diasDiferenca) {
        if (diasDiferenca == 0) {
            return Optional.of(BigDecimal.valueOf(3.00).add(valor.multiply(BigDecimal.valueOf(0.025))));
        } else if (diasDiferenca >= 1 && diasDiferenca <= 10) {
            return Optional.of(BigDecimal.valueOf(12.00));
        } else if (diasDiferenca >= 11 && diasDiferenca <= 20) {
            return Optional.of(valor.multiply(BigDecimal.valueOf(0.082)));
        } else if (diasDiferenca >= 21 && diasDiferenca <= 30) {
            return Optional.of(valor.multiply(BigDecimal.valueOf(0.069)));
        } else if (diasDiferenca >= 31 && diasDiferenca <= 40) {
            return Optional.of(valor.multiply(BigDecimal.valueOf(0.047)));
        } else if (diasDiferenca >= 41 && diasDiferenca <= 50) {
            return Optional.of(valor.multiply(BigDecimal.valueOf(0.017)));
        }
        return Optional.empty();
    }

    /**
     * Retorna lista de agendamentos.
     *
     * @return List<Agendamento> lista de agendamentos.
     */
    public List<Agendamento> listarAgendamento() {
        return agendamentoRepository.findAll();
    }

    /**
     * Verifica se o número fornecido possui exatamente 10 dígitos. Lança uma exceção se inválido.
     *
     * @param numero O número a ser verificado.
     * @param fluxoConta Tipo da conta (origem ou destino).
     * @throws NumeroInvalidoException Se o número não tiver exatamente 10 dígitos.
     */
    private void validaNumerodaConta(BigInteger numero, FluxoContaEnum fluxoConta) {
        if (!application.verificaSeNumeroPossuiCaracteresNecessarios(numero)) {
            throw new NumeroInvalidoException(
                    "O número da conta de " + fluxoConta + " deve ter exatamente 10 dígitos.");
        }
    }

    /**
     * Verifica se a data de transferência é válida (deve ser maior que a data atual).
     *
     * @param data A data de transferência a ser verificada.
     * @throws DataInvalidoException Se a data for inválida.
     */
    private void validaDataTransferencia(LocalDate data) {
        if (!application.verificaSeDataMaiorQueHoje(data.toString())) {
            throw new DataInvalidoException("A data do agendamento deve ser maior que hoje.");
        }
    }

    /**
     * Verifica se o valor da transferência é maior que zero.
     *
     * @param valor O valor da transferência a ser verificado.
     * @throws ValorTransacaoInvalidoException Se o valor for inválido (menor ou igual a zero).
     */
    private void validaValorTransferencia(BigDecimal valor) {
        if (!application.verificaSeValorMaiorQueZero(valor)) {
            throw new ValorTransacaoInvalidoException("O valor da transação deve ser maior que zero.");
        }
    }
}
