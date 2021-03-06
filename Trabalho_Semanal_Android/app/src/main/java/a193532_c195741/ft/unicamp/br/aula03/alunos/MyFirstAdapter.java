package a193532_c195741.ft.unicamp.br.aula03.alunos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import a193532_c195741.ft.unicamp.br.aula03.R;

public class MyFirstAdapter extends RecyclerView.Adapter {
    private ArrayList<Aluno> alunos;
    private int position;
    private MyOnItemClickListener myOnItemClickListener;


    public interface MyOnItemClickListener {
        void mostrarNomeAluno(String nome);
        void enviarPosicaoAluno(int index);
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    MyFirstAdapter(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout, parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    TextView txt = view.findViewById(R.id.nome);
                    myOnItemClickListener.mostrarNomeAluno(txt.getText().toString());
                }
            }
        });


        final MyFirstViewHolder viewHolder = new MyFirstViewHolder(view);
        view.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (myOnItemClickListener != null) {
                    myOnItemClickListener.enviarPosicaoAluno(viewHolder.position);
                    return true;
                }
                return false;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.position = position;
        ((MyFirstViewHolder) holder).onBind(alunos.get(position), position);

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }


    private class MyFirstViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nomeTextView;
        private TextView descricaoTextView;
        public int position;

        public MyFirstViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagem);
            nomeTextView = itemView.findViewById(R.id.nome);
            descricaoTextView = itemView.findViewById(R.id.descricao);
        }

        public void onBind(final Aluno aluno, final int position) {
            this.position = position;
            imageView.setImageResource(aluno.getFoto());
            nomeTextView.setText(aluno.getNome());
            descricaoTextView.setText(Html.fromHtml(aluno.getDescricao()));
        }

    }
}
