package com.example.trabalhoprimeirobimestre;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edEmail;
    private EditText edIdade;
    private EditText edDisciplina;
    private EditText edNota1;
    private EditText edNota2;
    private TextView tvResultado;
    private TextView tvErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNome = findViewById(R.id.edNome);
        edEmail = findViewById(R.id.edEmail);
        edIdade = findViewById(R.id.edIdade);
        edDisciplina = findViewById(R.id.edDisciplina);
        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        tvResultado = findViewById(R.id.tvResultado);
        tvErro = findViewById(R.id.tvErro);

        Button btEnviar = findViewById(R.id.btEnviar);
        Button btLimpar = findViewById(R.id.btLimpar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void validarCampos() {
        String nome = edNome.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String idade = edIdade.getText().toString().trim();
        String disciplina = edDisciplina.getText().toString().trim();
        String nota1Bimestre = edNota1.getText().toString().trim();
        String nota2Bimestre = edNota2.getText().toString().trim();

        StringBuilder erros = new StringBuilder();
        if (nome.isEmpty()) erros.append("\"O campo de nome está vazio\n");
        if (email.isEmpty() || email.contains("@")) erros.append("\"O email é inválido\n");
        if (idade.isEmpty() || Integer.parseInt(idade) <= 0) erros.append("\"A\n" + "idade deve ser um número positivo\n");
        if (disciplina.isEmpty()) erros.append("O campo de disciplina está vazio.\n");
        if (nota1Bimestre.isEmpty() || nota2Bimestre.isEmpty() || !notaValida(nota1Bimestre) || !notaValida(nota2Bimestre)) {
            erros.append("O campo de notas deve conter um valor entre 0 e 10\n");
        }

        if (erros.length() > 0) {
            tvErro.setText(erros.toString());
            tvErro.setVisibility(View.VISIBLE);
            tvResultado.setText("");
        } else {
            tvErro.setVisibility(View.GONE);
            exibirResultado(nome, email, idade, disciplina, nota1Bimestre, nota2Bimestre);
        }
    }

    private boolean notaValida(String notaBimestre) {
        try {
            float nota = Float.parseFloat(notaBimestre);
            return nota >= 0 && nota <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void exibirResultado(String nome, String email, String idadeStr, String disciplina, String nota1Bimestre, String nota2Bimestre) {
        float nota1 = Float.parseFloat(nota1Bimestre);
        float nota2 = Float.parseFloat(nota2Bimestre);

        float media = (nota1 + nota2) / 2;

        String status;
        if (media >= 6) {
            status = "Aprovado";
        } else {
            status = "Reprovado";
        }

        String resultado = "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Idade: " + idadeStr + "\n" +
                "Disciplina: " + disciplina + "\n" +
                "Notas: " + nota1 + " e " + nota2 + "\n" +
                "Média: " + media + "\n" +
                "Status: " + status;

        tvResultado.setText(resultado);
    }


    private void limparCampos() {
        edNome.setText("");
        edEmail.setText("");
        edIdade.setText("");
        edDisciplina.setText("");
        edNota1.setText("");
        edNota2.setText("");
        tvResultado.setText("");
        tvErro.setVisibility(View.GONE);
    }
}
