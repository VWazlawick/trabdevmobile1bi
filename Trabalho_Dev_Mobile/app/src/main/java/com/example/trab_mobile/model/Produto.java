package com.example.trab_mobile.model;

public class Produto {

    private int codigoProduto;
    private String dsProduto;
    private double vlrProduto;

    public Produto() {
    }

    public Produto(int codigoProduto, String dsProduto, double vlrProduto) {
        this.codigoProduto = codigoProduto;
        this.dsProduto = dsProduto;
        this.vlrProduto = vlrProduto;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public double getVlrProduto() {
        return vlrProduto;
    }

    public void setVlrProduto(double vlrProduto) {
        this.vlrProduto = vlrProduto;
    }
}
