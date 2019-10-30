package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.Estatisticas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import a193532_c195741.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstatFrasesAluno extends Fragment {


    TextView textView;
    private View lview;

    public EstatFrasesAluno() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_estat_alunos_frase, container, false);
        }

        textView = lview.findViewById(R.id.estjogo3aluno);

        String comando = "GET";
        String urlGet = "https://estatisticasjogo3.firebaseio.com/Database/Jogo_AlunosFrase/.json";

        new EstFirebaseJogo3Alunos(this).execute(urlGet, comando);

        return lview;
    }

    public void atualizaLayoult(JSONObject jsonObject){

    }

}
