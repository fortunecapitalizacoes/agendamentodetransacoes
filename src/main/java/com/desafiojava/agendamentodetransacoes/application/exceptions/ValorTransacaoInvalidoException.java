package com.desafiojava.agendamentodetransacoes.application.exceptions;

/**
 * Exceção lançada quando o valor da transação é inválido.
 * 
 * Esta exceção é uma subclasse de {@link RuntimeException} e é usada para indicar
 * que o valor da transação fornecido é inválido, como ser menor ou igual a zero.
 */
public class ValorTransacaoInvalidoException extends RuntimeException {

    /**
     * Construtor da exceção {@link ValorTransacaoInvalidoException}.
     *
     * @param mensagem A mensagem detalhada que descreve o motivo da exceção.
     */
    public ValorTransacaoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
