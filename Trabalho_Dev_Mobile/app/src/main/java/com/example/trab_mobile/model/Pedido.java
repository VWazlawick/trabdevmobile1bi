package com.example.trab_mobile.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pedido {

    private int id;
    private Cliente cliente;
    private ArrayList<ProdutoPedido> listaProduto;
    private FormaPagamento formaPagamento;
    private int quantParcelas;

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ProdutoPedido> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(ArrayList<ProdutoPedido> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getQuantParcelas() {
        return quantParcelas;
    }

    public void setQuantParcelas(int quantParcelas) {
        this.quantParcelas = quantParcelas;
    }
}
