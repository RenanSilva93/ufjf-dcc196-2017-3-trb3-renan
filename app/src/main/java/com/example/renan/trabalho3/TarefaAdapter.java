package com.example.renan.trabalho3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by renan on 02/12/2017.
 */

public class TarefaAdapter extends CursorAdapter {
    private TarefadbHelper tarefadbHelper;

    public TarefaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        tarefadbHelper = new TarefadbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.lista_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo = (TextView) view.findViewById(R.id.textView);
        TextView txtEstado = (TextView) view.findViewById(R.id.textView2);
        String titulo = cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_TITULO));
        String estado = cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_ESTADO));
        txtTitulo.setText(titulo);
        if(estado.equals("0")) {
            txtEstado.setText(" A Fazer");
        } else if(estado.equals("1")) {
            txtEstado.setText("Em Execução");
        } else if(estado.equals("2")) {
            txtEstado.setText("Bloqueada");
        } else if(estado.equals("3")) {
            txtEstado.setText("Concluída");
        }
    }

    public void inserirTarefa(Tarefa tarefa){
        tarefadbHelper.inserirTarefa(tarefa);

    }

    public void atualizarTarefa(){
        this.changeCursor(tarefadbHelper.atualizarTarefa());

    }

    public Tarefa getTarefa(int index) {
        return tarefadbHelper.getTarefa(index);
    }

    public void editarTarefa(Tarefa tarefa, int index){
        tarefadbHelper.editarTarefa(tarefa, index);

    }

    public Cursor getTarefasTags(String tag) {
        return tarefadbHelper.getTarefasTags(tag);
    }

    public void excluirTarefa(int index) {
        tarefadbHelper.excluirTarefa(index);
    }

}
