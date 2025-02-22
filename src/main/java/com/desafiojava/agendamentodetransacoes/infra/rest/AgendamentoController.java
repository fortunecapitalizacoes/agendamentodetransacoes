package com.desafiojava.agendamentodetransacoes.infra.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.desafiojava.agendamentodetransacoes.domain.AgendamentoDomain;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;

/**
 * Controlador de agendamentos de transações financeiras.
 * 
 * Esta classe fornece as rotas REST para a criação e listagem de agendamentos de transferência
 * entre contas bancárias. Utiliza o padrão RESTful com os métodos HTTP POST e GET.
 */
@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
@Tag(name = "Agendamento", description = "Operações relacionadas ao agendamento de transações financeiras")
public class AgendamentoController {
    
    private final AgendamentoDomain domain;

    /**
     * Agendar uma nova transação de transferência.
     * 
     * Este endpoint cria um novo agendamento de transferência entre duas contas. A requisição
     * deve conter os dados necessários para o agendamento, como contas de origem e destino, 
     * valor e data da transferência.
     *
     * @param request Os dados do agendamento a ser criado.
     * @return A resposta com os dados do agendamento realizado.
     */
    @PostMapping()
    @Operation(summary = "Agendar uma nova transação", 
               description = "Cria um novo agendamento de transferência entre contas de origem e destino.")
    public ResponseEntity<AgendamentoResponse> agendarTransacao(
            @Parameter(description = "Dados do agendamento a ser criado") 
            @RequestBody AgendamentoRequest request) {
        AgendamentoResponse response = domain.agendarTransacao(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar todos os agendamentos de transações.
     * 
     * Este endpoint retorna uma lista de todos os agendamentos de transferências feitas no sistema.
     * 
     * @return A lista de todos os agendamentos realizados.
     */
    @GetMapping()
    @Operation(summary = "Listar todos os agendamentos", 
               description = "Retorna todos os agendamentos de transferência registrados no sistema.")
    public ResponseEntity<?> listarAgendamentos() {
        return ResponseEntity.ok(domain.listarAgendamento());
    }
}
