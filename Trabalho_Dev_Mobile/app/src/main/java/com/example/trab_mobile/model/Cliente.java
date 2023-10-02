package com.example.trab_mobile.model;

public class Cliente {

    private String nmCliente;
    private String cpf;

    public Cliente() {
    }

    public Cliente(String nmCliente, String cpf) {
        this.nmCliente = nmCliente;
        this.cpf = cpf;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
