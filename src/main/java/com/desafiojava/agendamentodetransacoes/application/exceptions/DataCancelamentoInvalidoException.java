package com.desafiojava.agendamentodetransacoes.application.exceptions;

/**
 * Exceção lançada quando a data fornecida é inválida.
 * 
 * Esta exceção é uma subclasse de {@link RuntimeException} e é usada para indicar
 * que a data de cancelamento fornecida não é válida.
 */
public class DataCancelamentoInvalidoException extends RuntimeException {

    /**
     * Construtor da exceção {@link DataCancelamentoInvalidoException}.
     *
     * @param mensagem A mensagem detalhada que descreve o motivo da exceção.
     */
    public DataCancelamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
