package com.brt.braianitech.bibliotecasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Braiani on 23/06/2017.
 */

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String addLivro(String titulo, String autor, String editora){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(banco.TITULO, titulo);
        valores.put(banco.AUTOR, autor);
        valores.put(banco.EDITORA, editora);

        resultado = db.insertOrThrow(banco.TABELA, null, valores);
        db.close();

        if (resultado == -1){
            return "Erro ao adicionar o livro!";
        }else {
            return "Livro adicionado com sucesso!";
        }
    }

    public void removeLivro(int id){
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA, where, null);
        db.close();
    }

    public Cursor carregaLivros(){
        Cursor cursor;
        String[] campos = {banco.ID, banco.TITULO, banco.AUTOR, banco.EDITORA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor carregaLivroPorId(int id){
        Cursor cursor;
        String[] campos = {banco.ID, banco.TITULO, banco.AUTOR, banco.EDITORA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, where, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void atualizarLivro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(banco.TITULO, titulo);
        valores.put(banco.AUTOR, autor);
        valores.put(banco.EDITORA, editora);

        db.update(CriaBanco.TABELA, valores, where, null);
        db.close();

    }
}
