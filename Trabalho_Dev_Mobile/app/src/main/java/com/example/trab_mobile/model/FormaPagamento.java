package com.example.trab_mobile.model;

public enum FormaPagamento {
    A_VISTA("A vista"),
    A_PRAZO("A prazo");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
