package com.desafiojava.agendamentodetransacoes.infra.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiojava.agendamentodetransacoes.application.enuns.StatusEnum;
import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositório para persistência e recuperação de dados de agendamentos.
 * 
 * Esta interface estende JpaRepository, permitindo realizar operações 
 * de CRUD (Create, Read, Update, Delete) no banco de dados.
 * Ela está associada à entidade {@link Agendamento}.
 */
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    /**
     * Retorna uma lista de agendamentos baseados em uma data de transferência específica.
     * 
     * @param dataTransferencia A data de transferência a ser filtrada.
     * @return Lista de {@link Agendamento} com a data de transferência fornecida.
     */
    List<Agendamento> findByDataTransferencia(LocalDate dataTransferencia);

    /**
     * Retorna uma lista de agendamentos com status específico.
     * 
     * @param status O status dos agendamentos a ser filtrado.
     * @return Lista de {@link Agendamento} com o status fornecido.
     */
    List<Agendamento> findByStatus(StatusEnum status);

    /**
     * Retorna uma lista de agendamentos com contas de origem e destino específicas.
     * 
     * @param contaOrigem A conta de origem a ser filtrada.
     * @param contaDestino A conta de destino a ser filtrada.
     * @return Lista de {@link Agendamento} que correspondem às contas fornecidas.
     */
    List<Agendamento> findByContaOrigemAndContaDestino(BigInteger contaOrigem, BigInteger contaDestino);
}
