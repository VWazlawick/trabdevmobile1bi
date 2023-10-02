package com.example.trab_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trab_mobile.model.Cliente;
import com.example.trab_mobile.model.FormaPagamento;
import com.example.trab_mobile.model.Pedido;
import com.example.trab_mobile.model.Produto;
import com.example.trab_mobile.model.ProdutoPedido;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LancamentoPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView tvAddCliente;
    private AutoCompleteTextView tvAddProduto;
    private EditText edQuantidade;
    private TextView tvValorUnitario;
    private ImageButton btAddProduto;
    private TextView tvListaProduto;
    private TextView tvQuantidadeTotal;
    private TextView tvValorTotal;
    private Spinner spFormaPagamento;
    private TextView tvParcelas;
    private EditText edParcelas;
    private Button btConcluir;
    private Button btSairPedido;
    private TextView tvErroFormaPagamento;
    private ImageButton btPesquisar;
    private EditText edCodigoPedido;

    private ArrayList<Cliente> listaCliente;
    private ArrayList<Produto> listaProduto;
    private ArrayList<ProdutoPedido> listaProdutoPedido;
    private FormaPagamento fp = null;
    private int posicaoProduto = 0;
    private int posicaoCliente = 0;
    private int posicaoFormaPagamento = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_pedido);

        tvAddCliente = findViewById(R.id.tvAddCliente);
        tvAddProduto = findViewById(R.id.tvAddProduto);
        edQuantidade = findViewById(R.id.edQuantidade);
        tvValorUnitario = findViewById(R.id.tvValorUnitario);
        btAddProduto = findViewById(R.id.btAddProduto);
        tvListaProduto = findViewById(R.id.tvListaProduto);
        tvQuantidadeTotal = findViewById(R.id.tvQuantidadeTotal);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        spFormaPagamento = findViewById(R.id.spFormaPagamento);
        tvParcelas = findViewById(R.id.tvParcelas);
        edParcelas = findViewById(R.id.edParcelas);
        btConcluir = findViewById(R.id.btConcluir);
        btSairPedido = findViewById(R.id.btSairPedido);
        tvErroFormaPagamento = findViewById(R.id.tvErroFormaPagamento);
        edCodigoPedido = findViewById(R.id.edCodigoPedido);
        btPesquisar = findViewById(R.id.btPesquisar);

        btSairPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                listaProdutoPedido.clear();
                tvAddCliente.setText("");
                spFormaPagamento.setSelection(0);
                tvListaProduto.setText("");
                tvValorTotal.setText("");
                tvQuantidadeTotal.setText("");
            }
        });

        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarProduto();
            }
        });

        spFormaPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if(posicao>0) {
                    tvErroFormaPagamento.setVisibility(View.GONE);
                    if(spFormaPagamento.getSelectedItemPosition() == 1){
                        fp = FormaPagamento.A_PRAZO;
                        edParcelas.setVisibility(View.VISIBLE);
                        atualizarValorTotal();
                    }else if(spFormaPagamento.getSelectedItemPosition() == 2){
                        fp = FormaPagamento.A_VISTA;
                        edParcelas.setVisibility(View.GONE);
                        atualizarValorTotal();
                    }else{
                        fp = null;
                    }
                }else{
                    tvErroFormaPagamento.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvAddCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if(posicao>0){
                    posicaoCliente = posicao;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvAddProduto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                posicaoProduto = posicao;
                tvValorUnitario.setText("R$"+listaProduto.get(posicaoProduto).getVlrProduto());
            }
        });
        
        btConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                concluirPedido();                
            }
        });

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarPedido();
            }
        });

        edCodigoPedido.setHint("Código: " + (Controller.getInstance().retornarProduto().size()));
        carregarCliente();
        carregarProduto();
        carregarFormaPagamento();
        atualizarValorTotal();
    }

    private void buscarPedido() {
        ArrayList<Pedido> listaPedido;
        listaPedido = Controller.getInstance().retornPedidos();

        for(int i =0;i<listaPedido.size()-1;i++){
            for(int j = 0;j<listaPedido.size()-i-1;j++){
                int aux = listaPedido.get(j+1).getId();
                listaPedido.get(j).setId(listaPedido.get(j+1).getId());
                listaPedido.get(j+1).setId(aux);
            }
        }

        for(int i=0;i<listaPedido.size();i++){
            if(edCodigoPedido.equals(listaPedido.get(i).getId())){
                Pedido p1 = Controller.getInstance().retornPedidos().get(i);

                listaProdutoPedido = p1.getListaProduto();
                String txt = "";
                atualizarLista();
                atualizarValorTotal();
                tvAddCliente.setText(p1.getCliente().getNmCliente());
            }
        }
    }

    private void concluirPedido() {
        if(listaProdutoPedido.isEmpty()){
            tvAddProduto.setError("Informe no minímo um produto");
        }
        else if(fp == FormaPagamento.A_PRAZO){
            if(edParcelas.getText().toString().isEmpty()){
                edParcelas.setError("Informe a quantidade de parcelas");
            }
        }else{
            Pedido pd1 = new Pedido();

            pd1.setId(Controller.getInstance().retornPedidos().size());
            pd1.setCliente(listaCliente.get(posicaoCliente));
            pd1.setListaProduto(listaProdutoPedido);
            pd1.setFormaPagamento(fp);
            if(fp==FormaPagamento.A_PRAZO){
                pd1.setQuantParcelas(Integer.parseInt(edParcelas.getText().toString()));
            }

            Controller.getInstance().salvarPedido(pd1);

            Toast.makeText(this, "Venda Concluida!", Toast.LENGTH_SHORT).show();
            listaProdutoPedido.clear();
            tvAddCliente.setText("");
            spFormaPagamento.setSelection(0);
            tvListaProduto.setText("");
            tvValorTotal.setText("");
            tvQuantidadeTotal.setText("");
            tvValorUnitario.setText("");
            edCodigoPedido.setHint("Código:" + (Controller.getInstance().retornPedidos().size()));
        }

        
    }

    private void adicionarProduto() {
        if(edQuantidade.getText().toString().isEmpty() || Integer.parseInt(edQuantidade.getText().toString())<1){
            if(tvAddProduto.getText().toString().isEmpty()){
                tvAddProduto.setError("Informe o Produto");
            }
            if(edQuantidade.getText().toString().isEmpty()){
                edQuantidade.setError("Informe a quantidade");
            }
            if(Integer.parseInt(edQuantidade.getText().toString())<1){
                edQuantidade.setError("Informe pelo menos 1 quantidade");
            }
        }else{
            ProdutoPedido pp1 = new ProdutoPedido();
            Produto p1 = listaProduto.get(posicaoProduto);
            pp1.setProduto(p1);
            pp1.setQuantidade(Integer.parseInt(edQuantidade.getText().toString()));

            Controller.getInstance().salvarProdutoPedido(pp1);

            tvAddProduto.setText("");
            edQuantidade.setText("");
            tvQuantidadeTotal.setText("");
            tvValorUnitario.setText("");

            atualizarLista();
            atualizarValorTotal();
        }


    }

    private void atualizarValorTotal() {
        String msgValor = "";
        String msgQuant = "";
        double valorTotal = 0;
        int qtndTotal = 0;

        listaProdutoPedido = Controller.getInstance().retornarProdutoPedido();

        for(int i = 0;i<listaProdutoPedido.size();i++){
            valorTotal += (listaProdutoPedido.get(i).getProduto().getVlrProduto() * listaProdutoPedido.get(i).getQuantidade());
            qtndTotal += listaProdutoPedido.get(i).getQuantidade();
            msgQuant = "Quantidade Total: " + qtndTotal;
        }

        if(fp!=null){
            if(fp.equals(FormaPagamento.A_VISTA)){
                valorTotal = valorTotal * 0.95;
                msgValor = "Valor Total R$" + valorTotal;
            } else if (fp.equals(FormaPagamento.A_PRAZO)) {
                valorTotal = valorTotal * 1.05;
                msgValor = "Valor Total R$" + valorTotal;
            }
        }else{
            msgValor = "Valor Total R$" + valorTotal;
        }
        tvValorTotal.setText(msgValor);
        tvQuantidadeTotal.setText(msgQuant);
    }

    private void atualizarLista() {
        String txt = "";

        listaProdutoPedido = Controller.getInstance().retornarProdutoPedido();
        for(int i =0;i<listaProdutoPedido.size();i++){
            txt+= listaProdutoPedido.get(i).getProduto().getDsProduto() + "        -        Quantidade:" +
                    listaProdutoPedido.get(i).getQuantidade() + "        -        R$" +
                    (listaProdutoPedido.get(i).getQuantidade()*listaProdutoPedido.get(i).getProduto().getVlrProduto()) + "\n";
        }

        tvListaProduto.setText(txt);
    }

    private void carregarFormaPagamento() {
        String vetFormaPagamento[] = new  String[3];
        vetFormaPagamento[0] = "";
        vetFormaPagamento[1] = FormaPagamento.A_PRAZO.getDescricao();
        vetFormaPagamento[2] = FormaPagamento.A_VISTA.getDescricao();
        ArrayAdapter adapter = new ArrayAdapter(LancamentoPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetFormaPagamento);

        spFormaPagamento.setAdapter(adapter);
    }

    private void carregarProduto() {
        listaProduto = Controller.getInstance().retornarProduto();
        String[]vetProduto = new String[listaProduto.size()];

        for(int i = 0;i<vetProduto.length;i++){
            Produto produto = listaProduto.get(i);
            vetProduto[i] = produto.getDsProduto();
        }
        ArrayAdapter adapter = new ArrayAdapter(LancamentoPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetProduto);
        tvAddProduto.setAdapter(adapter);
    }

    private void carregarCliente() {
        listaCliente = Controller.getInstance().retornarCliente();
        String[]vetCliente = new String[listaCliente.size()];

        for(int i = 0; i<vetCliente.length;i++){
            Cliente cliente = listaCliente.get(i);
            vetCliente[i] = cliente.getNmCliente();
        }

        ArrayAdapter adapter = new ArrayAdapter(LancamentoPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,vetCliente);

        tvAddCliente.setAdapter(adapter);

    }

}