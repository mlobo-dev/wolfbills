package com.wolftech.wolfbills.model.enums;

public enum StatusLancamento {

    PENDENTE("Pendente"),
    CANCELADO("Cancelado"),
    EFETIVADO("Efetivado");

    private String descricao;

    StatusLancamento(String descricao) {
        this.descricao = descricao;
    }

}
