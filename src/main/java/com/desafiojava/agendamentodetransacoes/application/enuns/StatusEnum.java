package com.desafiojava.agendamentodetransacoes.application.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {

    AGENDADO("Agendado"),
    CANCELADO("Cancelado");

    private final String descricao;

    // Método para obter o enum baseado na descrição
    public static StatusEnum valueOfDescricao(String descricao) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
