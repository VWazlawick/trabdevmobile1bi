package com.example.trab_mobile;

import com.example.trab_mobile.model.Cliente;
import com.example.trab_mobile.model.Pedido;
import com.example.trab_mobile.model.Produto;
import com.example.trab_mobile.model.ProdutoPedido;

import java.util.ArrayList;

public class Controller {

    private static Controller instancia;
    private static ArrayList<Cliente> listaCliente;
    private static ArrayList<Produto> listaProduto;
    private static ArrayList<ProdutoPedido> listaProdutoPedido;
    private static ArrayList<Pedido> listaPedido;

    public static Controller getInstance(){
        if(instancia == null){
            return instancia = new Controller();
        }else{
            return instancia;
        }
    }

    private Controller(){
        listaCliente = new ArrayList<>();
        listaProduto = new ArrayList<>();
        listaProdutoPedido = new ArrayList<>();
        listaPedido = new ArrayList<>();
    }
    public void salvarCliente(Cliente cliente){
        listaCliente.add(cliente);
    }
    public void salvarProduto(Produto produto){
        listaProduto.add(produto);
    }
    public void salvarProdutoPedido(ProdutoPedido produtoPedido){
        listaProdutoPedido.add(produtoPedido);
    }
    public void salvarPedido(Pedido pedido){
        listaPedido.add(pedido);
    }
    public ArrayList<Cliente> retornarCliente(){
        return listaCliente;
    }

    public ArrayList<Produto> retornarProduto(){
        return listaProduto;
    }
    public ArrayList<ProdutoPedido> retornarProdutoPedido(){return listaProdutoPedido;}
    public ArrayList<Pedido> retornPedidos(){
        return listaPedido;
    }
}
