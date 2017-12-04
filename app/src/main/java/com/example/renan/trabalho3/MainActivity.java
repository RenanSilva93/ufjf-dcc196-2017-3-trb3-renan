package com.example.renan.trabalho3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Button btnCadastrar;
    private Button btnTags;
    private ListView lstTarefa;
    public static TarefaAdapter adapterTarefa;
    public static TagAdapter adapterTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button) findViewById(R.id.idCadastrar);
        btnTags = (Button) findViewById(R.id.idTags);
        lstTarefa = (ListView) findViewById(R.id.idListaTarefa);

        adapterTag = new TagAdapter(getBaseContext(), null);
        adapterTarefa = new TarefaAdapter(getBaseContext(), null);
        adapterTarefa.atualizarTarefa();
        lstTarefa.setAdapter(adapterTarefa);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, cadastrar_tarefa.class);
                intent.putExtra("id", "novo");
                startActivity(intent);
            }
        });

        btnTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, tags.class);
                startActivity(intent);
            }
        });

        lstTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j = (int) l;
                Intent atividade = new Intent(MainActivity.this, cadastrar_tarefa.class);
                atividade.putExtra("id", Integer.toString(j));
                startActivity(atividade);
            }
        });

        lstTarefa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j = (int) l;

                adapterTarefa.excluirTarefa(j);
                adapterTag.excluirTag(j);

                adapterTarefa.atualizarTarefa();
                adapterTag.atualizarTags();
                return true;
            }
        });

    }
}
