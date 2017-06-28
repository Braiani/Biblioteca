package com.brt.braianitech.bibliotecasqlite;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Braiani on 23/06/2017.
 */

public class AddLivro extends AppCompatActivity {
    EditText txtTitulo, txtAutor, txtEditora;
    Button btnAdd;
    String titulo, autor, editora, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_livro_layout);
        encontrarViews();
        btnAdd.setOnClickListener(ouvinte);
    }

    private void encontrarViews(){
        txtAutor = (EditText) findViewById(R.id.txtAutor);
        txtEditora = (EditText) findViewById(R.id.txtEditora);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        btnAdd = (Button) findViewById(R.id.btnAddLivro);
    }

    View.OnClickListener ouvinte = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BancoController crud = new BancoController(getApplicationContext());
            titulo = txtTitulo.getText().toString();
            autor = txtAutor.getText().toString();
            editora = txtEditora.getText().toString();

            resultado = crud.addLivro(titulo, autor, editora);
            if (resultado.equals("Livro adicionado com sucesso!")){
                txtTitulo.setText("");
                txtAutor.setText("");
                txtEditora.setText("");
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

