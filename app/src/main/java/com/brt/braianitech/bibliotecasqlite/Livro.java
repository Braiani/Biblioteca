package com.brt.braianitech.bibliotecasqlite;

/**
 * Created by Braiani on 24/06/2017.
 */

public class Livro {

    private String titulo, autor, editora, id;

    public Livro() {
    }

    public Livro(String titulo, String autor, String editora, String id) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
