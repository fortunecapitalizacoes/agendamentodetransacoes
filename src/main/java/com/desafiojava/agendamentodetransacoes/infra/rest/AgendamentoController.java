package com.desafiojava.agendamentodetransacoes.infra.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {
	@PostMapping("/agendar-transacao")
	public ResponseEntity<?> agendarTransacao(@RequestBody AgendamentoRequest request) {
		return ResponseEntity.ok(AgendamentoResponse.builder().build());
	}
}
