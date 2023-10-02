package com.example.trab_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trab_mobile.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNmCliente;
    private EditText edCpfCliente;
    private Button btSalvarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        edNmCliente = findViewById(R.id.edNmCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalvarCliente();
            }
        });

    }

    private void SalvarCliente() {
        if(edCpfCliente.getText().toString().isEmpty() || edNmCliente.getText().toString().isEmpty()){
            if(edNmCliente.getText().toString().isEmpty()){
                edNmCliente.setError("Informe o Nome do Cliente");
            }
            if(edCpfCliente.getText().toString().isEmpty()){
                edCpfCliente.setError("Informe o CPF do Cliente");
            }
        }else{
            Cliente c1 = new Cliente();
            c1.setNmCliente(edNmCliente.getText().toString());
            c1.setCpf(edCpfCliente.getText().toString());

            Controller.getInstance().salvarCliente(c1);
            Toast.makeText(this, "Cliente Cadastrado com Sucesso!!", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
}