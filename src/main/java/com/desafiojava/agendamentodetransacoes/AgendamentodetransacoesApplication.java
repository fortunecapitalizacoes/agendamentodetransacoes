package com.desafiojava.agendamentodetransacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Classe principal para a aplicação de agendamento de transações financeiras.
 * 
 * Esta classe inicializa o Spring Boot e configura a execução da aplicação, permitindo
 * o agendamento de transferências de valores entre contas. Ela também inclui configurações
 * do Swagger para a documentação da API.
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API de Agendamento de Transações",
        version = "1.0",
        description = "API para agendamento e gerenciamento de transações bancárias"
    )
)
public class AgendamentodetransacoesApplication {

    /**
     * Método principal para inicializar a aplicação Spring Boot.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(AgendamentodetransacoesApplication.class, args);
    }
}
