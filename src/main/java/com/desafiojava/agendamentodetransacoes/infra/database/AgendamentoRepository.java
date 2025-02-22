package com.desafiojava.agendamentodetransacoes.infra.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

   
}
