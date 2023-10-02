package com.example.trab_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trab_mobile.model.Produto;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;

public class CadastroProdutoActivity extends AppCompatActivity {

    private TextView tvCodigoProduto;
    private EditText edDescricaoProduto;
    private EditText edValorUnitario;
    private Button btSalvarProduto;
    private TextView tvVisuListaProduto;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        
        tvCodigoProduto = findViewById(R.id.tvCodigoProduto);
        edDescricaoProduto = findViewById(R.id.edDescricaoProduto);
        edValorUnitario = findViewById(R.id.edValorUnitario);
        btSalvarProduto = findViewById(R.id.btSalvarProduto);
        tvVisuListaProduto = findViewById(R.id.tvVisuListaProduto);
        
        btSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarProduto();
            }
        });
        tvCodigoProduto.setText("0"+ (Controller.getInstance().retornarProduto().size()+1));
        atualizarListaProdutos();
    }

    private void atualizarListaProdutos() {
        ArrayList<Produto> lista = Controller.getInstance().retornarProduto();
        String txt = "";

        for (Produto produto : lista) {
            txt += produto.getCodigoProduto() + " - " +produto.getDsProduto() + " - R$" + produto.getVlrProduto() + "\n";

        }
        tvVisuListaProduto.setText(txt);
    }

    boolean teste = true;
    private void salvarProduto() {
        if(edDescricaoProduto.getText().toString().isEmpty() || edValorUnitario.getText().toString().isEmpty() || Integer.parseInt(edValorUnitario.getText().toString())<=0){
            if(edDescricaoProduto.getText().toString().isEmpty()){
                edDescricaoProduto.setError("Informe a Descrição!");
            }
            if(edValorUnitario.getText().toString().isEmpty()){
                edValorUnitario.setError("Informe o Valor Unitário!");
            }
            if (Integer.parseInt(edValorUnitario.getText().toString())<=0){
                edValorUnitario.setError("Informe um valor maior que 0");
            }
        }else {
            Produto p1 = new Produto();



            p1.setCodigoProduto(Controller.getInstance().retornarProduto().size()+1);
            p1.setDsProduto(edDescricaoProduto.getText().toString());
            p1.setVlrProduto(Double.parseDouble(edValorUnitario.getText().toString()));

            Controller.getInstance().salvarProduto(p1);
            Toast.makeText(this, "Produto Cadastrado com Sucesso!!", Toast.LENGTH_SHORT).show();
            finish();
        }




    }
}