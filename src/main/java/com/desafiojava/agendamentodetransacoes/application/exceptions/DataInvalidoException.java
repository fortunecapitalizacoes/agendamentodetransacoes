package com.desafiojava.agendamentodetransacoes.application.exceptions;

/**
 * Exceção lançada quando a data fornecida é inválida.
 * 
 * Esta exceção é uma subclasse de {@link RuntimeException} e é usada para indicar
 * que a data de agendamento fornecida não é válida, como uma data anterior à data atual.
 */
public class DataInvalidoException extends RuntimeException {

    /**
     * Construtor da exceção {@link DataInvalidoException}.
     *
     * @param mensagem A mensagem detalhada que descreve o motivo da exceção.
     */
    public DataInvalidoException(String mensagem) {
        super(mensagem);
    }
}
