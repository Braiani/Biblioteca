package com.brt.braianitech.bibliotecasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by Braiani on 23/06/2017.
 */

public class AtualizarLivro extends AppCompatActivity {

    EditText edTitulo, edAutor, edEditora;
    TextView txtIdAtualiza;
    Button btnExcluir, btnAtualizar;
    Cursor cursor;
    BancoController crud;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualizar_livro_layout);
        encontrarViews();

        id = this.getIntent().getStringExtra("id");

        crud = new BancoController(getApplicationContext());

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO, CriaBanco.AUTOR, CriaBanco.EDITORA};

        cursor = crud.carregaLivroPorId(Integer.parseInt(id));
        txtIdAtualiza.setText(cursor.getString(cursor.getColumnIndex(nomeCampos[0])));
        edTitulo.setText(cursor.getString(cursor.getColumnIndex(nomeCampos[1])));
        edAutor.setText(cursor.getString(cursor.getColumnIndex(nomeCampos[2])));
        edEditora.setText(cursor.getString(cursor.getColumnIndex(nomeCampos[3])));

        btnAtualizar.setOnClickListener(ouvinte);
        btnExcluir.setOnClickListener(ouvinte);
    }

    View.OnClickListener ouvinte = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnAtualizar:
                    atualizarRegistro(id);
                    break;
                case R.id.btnExcluir:
                    removerRegistro(id);
                    break;
            }
        }
    };

    private void atualizarRegistro(String codigo){
        String nLivro, nAutor, nEditora;
        nLivro = edTitulo.getText().toString();
        nAutor = edAutor.getText().toString();
        nEditora = edEditora.getText().toString();

        crud.atualizarLivro(Integer.parseInt(codigo), nLivro, nAutor, nEditora);

        retornarPrincipal();
    }

    private void removerRegistro(String codigo){
        crud.removeLivro(Integer.parseInt(codigo));
        retornarPrincipal();
    }

    private void retornarPrincipal(){
//        Intent intent = new Intent(AtualizarLivro.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

    private void encontrarViews(){
        edAutor = (EditText) findViewById(R.id.editAutor);
        edEditora = (EditText) findViewById(R.id.editEditora);
        edTitulo = (EditText) findViewById(R.id.editTitulo);
        txtIdAtualiza = (TextView) findViewById(R.id.txtIdAtualiza);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
    }
}
