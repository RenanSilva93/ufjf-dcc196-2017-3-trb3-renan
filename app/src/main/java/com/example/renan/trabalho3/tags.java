package com.example.renan.trabalho3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class tags extends AppCompatActivity {
    private Spinner sp;
    private TextView tag;
    private Button salvar;
    public ListView lstTags;
    public static TarefaAdapter adapterTarefa;
    public static TagAdapter adapterTag;
    int idTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        tag = (TextView) findViewById(R.id.idTag);
        salvar = (Button) findViewById(R.id.idSalvarTag);
        lstTags = (ListView) findViewById(R.id.idListaTag);

        adapterTarefa = new TarefaAdapter(getBaseContext(), null);
        adapterTarefa.atualizarTarefa();
        sp = (Spinner) findViewById(R.id.spinner2);
        sp.setAdapter(adapterTarefa);

        adapterTag = new TagAdapter(getBaseContext(), null);
        adapterTag.atualizarTags();
        lstTags.setAdapter(adapterTag);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTarefa = (int) l;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.adapterTag.inserirTag(tag.getText().toString(), idTarefa);
                tag.setText("");
                Toast.makeText(tags.this, "Tag salva com sucesso!", Toast.LENGTH_SHORT).show();
                adapterTag.atualizarTags();
            }
        });

        lstTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j = (int) l;
                String texto = MainActivity.adapterTag.getTagNome(j);
                Intent atividade = new Intent(tags.this, lista_tarefa.class);
                atividade.putExtra("id", texto);
                startActivity(atividade);
            }
        });
    }

}
