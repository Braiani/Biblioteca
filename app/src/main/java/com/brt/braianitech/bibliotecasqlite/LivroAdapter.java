package com.brt.braianitech.bibliotecasqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Braiani on 24/06/2017.
 */

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolder>{

    private List<Livro> livros;
    private Context context;
    private OnDataSelected onDataSelected;


    public LivroAdapter(List<Livro> livros, Context context, OnDataSelected onDataSelected) {
        this.livros = livros;
        this.context = context;
        this.onDataSelected = onDataSelected;
    }

    @Override
    public LivroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_itens, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Livro livro = livros.get(position);
        holder.txtTitulo.setText(livro.getTitulo());
//        holder.txtAutor.setText(livro.getAutor());
//        holder.txtEdtora.setText(livro.getEditora());
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public static interface OnDataSelected {
        void onDataSelected(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitulo, txtAutor, txtEdtora;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    treatOnDataSelectedIfNecessary(v, getAdapterPosition());
                }
            });

            txtTitulo = (TextView) itemView.findViewById(R.id.titulo_livro);
//            txtAutor = (TextView) itemView.findViewById(R.id.autor_livro);
//            txtEdtora = (TextView) itemView.findViewById(R.id.editora_livro);
        }

    }

    private void treatOnDataSelectedIfNecessary(View view, int position) {
        if(onDataSelected != null) {
            onDataSelected.onDataSelected(view, position);
        }
    }
}
