package com.brt.braianitech.bibliotecasqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements LivroAdapter.OnDataSelected {

    private List<Livro> livros = new ArrayList<>();
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(MainActivity.this, AddLivro.class);
                    startActivity(intent);
                }catch (Exception erro){
                    Log.d("Botao Add", erro.getMessage());
                }
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        livros.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mCursor = recuperarLivros();

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);

            LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
            layout.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layout);
            RecyclerView.Adapter adapter = new LivroAdapter(livros, this, this);
            recyclerView.setAdapter(adapter);

        } catch (Exception erro) {
            Log.d("LivroList", erro.getMessage());
        }
    }

    //Função responsável por se conectar ao SQLite e recuperar as informações do banco
    private Cursor recuperarLivros(){

        BancoController crud = new BancoController(getApplicationContext());
        final Cursor cursor = crud.carregaLivros();

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO};

        cursor.moveToFirst();
        int i = 1;
        while (!cursor.isAfterLast()){
            Livro livro = new Livro();
            livro.setTitulo( "Livro " + i + ": " + cursor.getString(cursor.getColumnIndex(nomeCampos[1])));
            livros.add(livro);
            i++;
            cursor.moveToNext();
        }

        return cursor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDataSelected(View view, int position) {
        String id;
        mCursor.moveToPosition(position);
        id = mCursor.getString(mCursor.getColumnIndexOrThrow(CriaBanco.ID));
        Intent intent = new Intent(MainActivity.this, AtualizarLivro.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
