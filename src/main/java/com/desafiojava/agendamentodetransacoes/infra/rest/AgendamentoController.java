package com.desafiojava.agendamentodetransacoes.infra.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafiojava.agendamentodetransacoes.domain.AgendamentoDomain;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {
	
	private final AgendamentoDomain domain;
	
	@PostMapping()
	public ResponseEntity<?> agendarTransacao(@RequestBody AgendamentoRequest request) {
		return ResponseEntity.ok(domain.agendarTransacao(request));
	}
	
	@GetMapping()
	public ResponseEntity<?> listarAgendamentos() {
		return ResponseEntity.ok(domain.listarAgendamento());
	}
	
}
