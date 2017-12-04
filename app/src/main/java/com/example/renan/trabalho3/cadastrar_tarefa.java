package com.example.renan.trabalho3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class cadastrar_tarefa extends AppCompatActivity {
    private TextView titulo;
    private TextView descricao;
    private TextView dificuldade;
    private Button salvar;
    private Spinner sp;
    private String [] estados = new String[]{"A Fazer", "Em Execução", "Bloqueada", "Concluída"};
    private int idEstado;
    private String selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);

        selecionado = (String) getIntent().getSerializableExtra("id");

        titulo = (TextView) findViewById(R.id.idTitulo);
        descricao = (TextView) findViewById(R.id.idDescricao);
        dificuldade = (TextView) findViewById(R.id.idDificuldade);
        salvar = (Button) findViewById(R.id.btnSalvar);

        if(!selecionado.equals("novo")) {
            int aux = (int) Integer.parseInt(selecionado);
            Tarefa tarefa = MainActivity.adapterTarefa.getTarefa(aux);

            titulo.setText(tarefa.getTitulo());
            descricao.setText(tarefa.getDescricao());
            dificuldade.setText(tarefa.getDificuldade().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, estados);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp = (Spinner) findViewById(R.id.spinner);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idEstado = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarefa tarefa = new Tarefa(titulo.getText().toString(), descricao.getText().toString(),
                        Integer.parseInt(dificuldade.getText().toString()), idEstado);

                if(tarefa.getDificuldade() >= 1 && tarefa.getDificuldade() <= 5) {
                    if (!selecionado.equals("novo")) {
                        MainActivity.adapterTarefa.editarTarefa(tarefa, Integer.parseInt(selecionado));
                        Toast.makeText(cadastrar_tarefa.this, "Tarefa Editada com Sucesso", Toast.LENGTH_SHORT).show();
                        MainActivity.adapterTarefa.atualizarTarefa();
                        titulo.setText("");
                        descricao.setText("");
                        dificuldade.setText("");
                    } else {
                        MainActivity.adapterTarefa.inserirTarefa(tarefa);
                        Toast.makeText(cadastrar_tarefa.this, "Tarefa Cadastrada com Sucesso", Toast.LENGTH_SHORT).show();
                        MainActivity.adapterTarefa.atualizarTarefa();
                        titulo.setText("");
                        descricao.setText("");
                        dificuldade.setText("");
                    }
                } else {
                    Toast.makeText(cadastrar_tarefa.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
