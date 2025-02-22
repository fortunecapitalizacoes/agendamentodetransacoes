package com.desafiojava.agendamentodetransacoes.infra.rest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.desafiojava.agendamentodetransacoes.application.enuns.StatusEnum;
import com.desafiojava.agendamentodetransacoes.domain.AgendamentoDomain;
import com.desafiojava.agendamentodetransacoes.domain.model.Agendamento;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.AgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.request.CancelarAgendamentoRequest;
import com.desafiojava.agendamentodetransacoes.infra.rest.response.AgendamentoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

class AgendamentoControllerTest {

    @Mock
    private AgendamentoDomain domain;

    @InjectMocks
    private AgendamentoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgendarTransacao() {
        // Arrange
        AgendamentoRequest request = new AgendamentoRequest();
        AgendamentoResponse response = new AgendamentoResponse();
        when(domain.agendarTransacao(request)).thenReturn(response);

        // Act
        ResponseEntity<AgendamentoResponse> result = controller.agendarTransacao(request);

        // Assert
        assertEquals(ResponseEntity.ok(response), result);
        verify(domain, times(1)).agendarTransacao(request);
    }

    @Test
    void testListarAgendamentos() {
        // Arrange
        List<Agendamento> lista = Collections.emptyList(); // Alterado para List<Agendamento>
        when(domain.listarAgendamento()).thenReturn(lista);

        // Act
        ResponseEntity<?> result = controller.listarAgendamentos();

        // Assert
        assertEquals(ResponseEntity.ok(lista), result);
        verify(domain, times(1)).listarAgendamento();
    }


    @Test
    void testCancelarTransacao() {
        // Arrange
        Agendamento agendamentoMock = new Agendamento();
        agendamentoMock.setId(1L);
        agendamentoMock.setStatus(StatusEnum.CANCELADO);

        when(domain.cancelarAgendamento(any())).thenReturn(agendamentoMock);

        CancelarAgendamentoRequest request = new CancelarAgendamentoRequest();
        request.setIdAgendamento(1L);

        // Act
        ResponseEntity<?> response = controller.cancelarTransacao(request);

        // Assert
        assertEquals(ResponseEntity.ok("Agendamento cancelado"), response);
        verify(domain, times(1)).cancelarAgendamento(1L);
    }


}
