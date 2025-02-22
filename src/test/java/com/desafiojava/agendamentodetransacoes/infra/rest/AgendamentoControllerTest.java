package com.desafiojava.agendamentodetransacoes.infra.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiojava.agendamentodetransacoes.domain.AgendamentoDomain;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/agendamento")
@Tag(name = "Agendamento", description = "Endpoints para agendamento de transferências")
public class AgendamentoControllerTest {
	
	private final AgendamentoDomain domain;

	@PostMapping("/agendar-transacao")
	@Operation(summary = "Agendar uma transação", description = "Realiza o agendamento de uma transferência financeira")
	public ResponseEntity<AgendamentoResponse> agendarTransacao(@Valid @RequestBody AgendamentoRequest request) {
	    var response = domain.agendarTransacao(request);
		return ResponseEntity.ok(response);
	}
}
