package com.example.renan.trabalho3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by renan on 02/12/2017.
 */

public class TarefadbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tarefaTags.db";

    public TarefadbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TarefaContract.SQL_CREATE_TAREFA);
        sqLiteDatabase.execSQL(TarefaContract.SQL_CREATE_TAGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TarefaContract.SQL_DROP_TAREFA);
        sqLiteDatabase.execSQL(TarefaContract.SQL_DROP_TAGS);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion,newVersion);
    }

    public void inserirTarefa(Tarefa tarefa){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO, tarefa.getTitulo());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO, tarefa.getDescricao());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE, tarefa.getDificuldade().toString());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_ESTADO, tarefa.getStatus().toString());
            long id = db.insert(TarefaContract.Tarefa.TABLE_NAME, null, values);
            atualizarTarefa();
        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }

    public Cursor atualizarTarefa(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa._ID,
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };

            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, null, null, null, null,  TarefaContract.Tarefa.COLUMN_NAME_ESTADO + " ASC");

            return c;

        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
            return null;
        }
    }

    public Tarefa getTarefa(int index) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tarefa._ID,
                    TarefaContract.Tarefa.COLUMN_NAME_TITULO,
                    TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO,
                    TarefaContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE,
                    TarefaContract.Tarefa.COLUMN_NAME_ESTADO,
            };

            String selecao = TarefaContract.Tarefa._ID +"=?";
            String[] args = {Integer.toString(index)};
            Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao, selecao, args, null, null, null);
            c.moveToFirst();
            Tarefa tarefa = new Tarefa(c.getString(1), c.getString(2), Integer.parseInt(c.getString(3)), Integer.parseInt(c.getString(4)));
            return tarefa;

        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
            return null;
        }
    }

    public void editarTarefa(Tarefa tarefa, int index) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TarefaContract.Tarefa.COLUMN_NAME_TITULO, tarefa.getTitulo());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO, tarefa.getDescricao());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_GRAU_DIFICULDADE, tarefa.getDificuldade().toString());
            values.put(TarefaContract.Tarefa.COLUMN_NAME_ESTADO, tarefa.getStatus().toString());

            String selecao = TarefaContract.Tarefa._ID+"= ?";

            db.update(TarefaContract.Tarefa.TABLE_NAME, values, selecao, new String[]{Integer.toString(index)});
            atualizarTarefa();
        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }

    public void inserirTag(String tag, int tarefa){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TarefaContract.Tags.COLUMN_NAME_TAG, tag);
            values.put(TarefaContract.Tags.COLUMN_ID_TAREFA, tarefa);
            long id = db.insert(TarefaContract.Tags.TABLE_NAME, null, values);
            atualizarTags();
        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
        }
    }

    public Cursor atualizarTags(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tags._ID,
                    TarefaContract.Tags.COLUMN_NAME_TAG,
                    TarefaContract.Tags.COLUMN_ID_TAREFA,
            };

            Cursor c = db.query(TarefaContract.Tags.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
            return null;
        }
    }

    public Cursor getTarefasTags(String tag) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tags._ID,
                    TarefaContract.Tags.COLUMN_NAME_TAG,
                    TarefaContract.Tags.COLUMN_ID_TAREFA,
            };
            String selecao = TarefaContract.Tags.COLUMN_NAME_TAG +"=?";
            String[] args = {tag};

            Cursor c = db.query(TarefaContract.Tags.TABLE_NAME, visao, selecao, args, null, null, null);
            c.moveToFirst();
            return c;

        } catch (Exception e) {
            Log.e("TAREFA", e.getLocalizedMessage());
            Log.e("TAREFA", e.getStackTrace().toString());
            return null;
        }
    }

    public String getTagNome(int index) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    TarefaContract.Tags._ID,
                    TarefaContract.Tags.COLUMN_NAME_TAG,
                    TarefaContract.Tags.COLUMN_ID_TAREFA,
            };
            String selecao = TarefaContract.Tags._ID+"=?";
            Cursor c = db.query(TarefaContract.Tags.TABLE_NAME, visao, selecao, new String[]{Integer.toString(index)}, null, null, null);
            c.moveToFirst();
            return c.getString(1);
        } catch (Exception e) {
            Log.e("FEIRA", e.getLocalizedMessage());
            Log.e("FEIRA", e.getStackTrace().toString());
            return null;
        }
    }

    public void excluirTarefa(int index) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selecao = TarefaContract.Tarefa._ID+"=?";
            db.delete(TarefaContract.Tarefa.TABLE_NAME, selecao, new String[]{Integer.toString(index)});
        } catch (Exception e) {
            Log.e("FEIRA", e.getLocalizedMessage());
            Log.e("FEIRA", e.getStackTrace().toString());
        }
    }

    public void excluirTag(int index) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String selecao = TarefaContract.Tags.COLUMN_ID_TAREFA+"=?";
            db.delete(TarefaContract.Tags.TABLE_NAME, selecao, new String[]{Integer.toString(index)});
        } catch (Exception e) {
            Log.e("FEIRA", e.getLocalizedMessage());
            Log.e("FEIRA", e.getStackTrace().toString());
        }
    }

}
