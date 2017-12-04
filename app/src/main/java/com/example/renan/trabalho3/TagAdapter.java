package com.example.renan.trabalho3;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by renan on 02/12/2017.
 */

public class TagAdapter extends CursorAdapter {
    private TarefadbHelper tarefadbHelper;

    public TagAdapter(Context context, Cursor c) {
        super(context, c, 0);
        tarefadbHelper = new TarefadbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.lista_tag_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTag = (TextView) view.findViewById(R.id.textView5);
        String tag = cursor.getString(cursor.getColumnIndexOrThrow(TarefaContract.Tags.COLUMN_NAME_TAG));
        txtTag.setText(tag);
    }

    public void inserirTag(String tag, int tarefa){
        tarefadbHelper.inserirTag(tag, tarefa);

    }

    public void atualizarTags() {
        this.changeCursor(tarefadbHelper.atualizarTags());
    }

    public String getTagNome(int index) {
        return tarefadbHelper.getTagNome(index);
    }

    public void excluirTag(int index) {
        tarefadbHelper.excluirTag(index);
    }
}
