package com.example.renan.trabalho3;

import android.provider.BaseColumns;

/**
 * Created by renan on 01/12/2017.
 */

public class TarefaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";

    public static final String SQL_CREATE_TAREFA = "CREATE TABLE " + Tarefa.TABLE_NAME + " (" +
            Tarefa._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefa.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_DESCRICAO + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_GRAU_DIFICULDADE + TEXT_TYPE + SEP +
            Tarefa.COLUMN_NAME_ESTADO + TEXT_TYPE + ")";
    public static final String SQL_DROP_TAREFA = "DROP TABLE IF EXISTS " + Tarefa.TABLE_NAME;

    public static final String SQL_CREATE_TAGS = "CREATE TABLE " + Tags.TABLE_NAME + " (" +
            Tags._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tags.COLUMN_ID_TAREFA + INT_TYPE + SEP +
            Tags.COLUMN_NAME_TAG + TEXT_TYPE + ")";
    public static final String SQL_DROP_TAGS = "DROP TABLE IF EXISTS " + Tags.TABLE_NAME;

    public TarefaContract() {
    }

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefa";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_DESCRICAO = "descricao";
        public static final String COLUMN_NAME_GRAU_DIFICULDADE = "grau_dificuldade";
        public static final String COLUMN_NAME_ESTADO = "estado";
    }

    public static final class Tags implements BaseColumns {
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_ID_TAREFA = "id_tarefa";
        public static final String COLUMN_NAME_TAG = "tag";
    }
}
