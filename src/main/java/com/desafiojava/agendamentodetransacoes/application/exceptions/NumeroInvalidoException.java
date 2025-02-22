package com.desafiojava.agendamentodetransacoes.application.exceptions;

/**
 * Exceção lançada quando o número da conta é inválido.
 * 
 * Esta exceção é uma subclasse de {@link RuntimeException} e é usada para indicar
 * que o número da conta fornecido não atende aos requisitos necessários, como
 * ter exatamente 10 dígitos.
 */
public class NumeroInvalidoException extends RuntimeException {

    /**
     * Construtor da exceção {@link NumeroInvalidoException}.
     *
     * @param mensagem A mensagem detalhada que descreve o motivo da exceção.
     */
    public NumeroInvalidoException(String mensagem) {
        super(mensagem);
    }
}
