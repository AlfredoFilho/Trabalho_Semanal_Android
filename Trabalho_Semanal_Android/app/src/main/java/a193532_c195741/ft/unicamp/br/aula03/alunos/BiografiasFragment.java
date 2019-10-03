package a193532_c195741.ft.unicamp.br.aula03.alunos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import a193532_c195741.ft.unicamp.br.aula03.MailFragment;
import a193532_c195741.ft.unicamp.br.aula03.MainActivity;
import a193532_c195741.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiografiasFragment extends Fragment {
    View view;
    int contador;
    private int position;
    ImageView imageView;
    TextView textView;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public BiografiasFragment() {
        contador = 0;

    }

    public void atualizaBiografia(int position){
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_biografias, container, false);
        }

        imageView = view.findViewById(R.id.imgAluno);
        textView = view.findViewById(R.id.txtBiografia);
        //super.onStart();

        Button buttonAnterior = view.findViewById(R.id.btnAnterior);
        Button buttonProximo = view.findViewById(R.id.btnProximo);
        mostrarAluno();


        buttonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(contador>0){
                   contador--;
                   atualizaBiografia(contador);
                   mostrarAluno();
               } else {
                   contador = Alunos.alunos.length - 1;
                   atualizaBiografia(contador);
                   mostrarAluno();
               }
            }
        });

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador < Alunos.alunos.length - 1) {
                    atualizaBiografia(contador);
                    contador++;
                    mostrarAluno();
                } else {
                    contador = 0;
                    atualizaBiografia(contador);
                    mostrarAluno();
                }
            }
        });

        return view;
    }

    public void mostrarAluno() {
        Aluno aluno = Alunos.alunos[position];
        imageView.setImageResource(aluno.getFoto());
        textView.setText(Html.fromHtml(aluno.getDescricao()));
    }
}
