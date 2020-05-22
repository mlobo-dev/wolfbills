package com.wolftech.wolfbills.model.enums;

public enum TipoLancamento {

    DESPESA("Despesa"),
    RECEITA("Receita");

    private String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

}
