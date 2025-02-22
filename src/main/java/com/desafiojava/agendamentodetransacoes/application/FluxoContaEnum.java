package com.desafiojava.agendamentodetransacoes.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum que representa os tipos de fluxo de conta para uma transação.
 * 
 * O fluxo pode ser de origem ou destino, utilizado para identificar
 * as contas envolvidas em uma transação financeira.
 */
@Getter
@RequiredArgsConstructor
public enum FluxoContaEnum {

    /**
     * Representa a conta de origem da transação.
     */
    ORIGEM("Origem"),

    /**
     * Representa a conta de destino da transação.
     */
    DESTINO("Destino");

    private final String descricao;

    /**
     * Obtém o valor do enum com base na descrição fornecida.
     * 
     * @param descricao A descrição da conta (origem ou destino).
     * @return O enum {@link FluxoContaEnum} correspondente à descrição.
     * @throws IllegalArgumentException Se a descrição não for válida.
     */
    public static FluxoContaEnum valueOfDescricao(String descricao) {
        for (FluxoContaEnum fluxo : FluxoContaEnum.values()) {
            if (fluxo.getDescricao().equalsIgnoreCase(descricao)) {
                return fluxo;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
