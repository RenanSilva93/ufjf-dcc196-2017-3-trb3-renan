package com.example.renan.trabalho3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class lista_tarefa extends AppCompatActivity {
    private TextView titulo;
    private ListView lstTarefa;
    public TarefaAdapter adapterTarefa;
    private String selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);

        selecionado = (String) getIntent().getSerializableExtra("id");

        titulo = (TextView) findViewById(R.id.textView9);
        lstTarefa = (ListView) findViewById(R.id.listaTarefa);

        titulo.setText(selecionado);

        adapterTarefa = new TarefaAdapter(getApplicationContext(), MainActivity.adapterTarefa.getTarefasTags(selecionado));

        ArrayList<String> tarefa = new ArrayList<>();

        for(int i=0; i<adapterTarefa.getCursor().getCount(); i++) {
            Log.d("teste", adapterTarefa.getCursor().getString(adapterTarefa.getCursor().getColumnIndexOrThrow(TarefaContract.Tags.COLUMN_ID_TAREFA)));

            Tarefa auxTarefa = MainActivity.adapterTarefa.getTarefa(Integer.parseInt(adapterTarefa.getCursor().getString(2)));
            tarefa.add(auxTarefa.getTitulo());
            adapterTarefa.getCursor().moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, tarefa);

        lstTarefa.setAdapter(adapter);
    }

}
