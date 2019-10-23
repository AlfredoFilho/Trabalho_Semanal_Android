package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import a193532_c195741.ft.unicamp.br.aula03.MainActivity;
import a193532_c195741.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Jogo3Fragment extends Fragment {

    private View lview;
    private RadioButton alunosFrase;
    private RadioButton frasesAluno;
    private RadioGroup radioGroup;

    public Jogo3Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_jogo3, container, false);
        }

        alunosFrase = lview.findViewById(R.id.alunosFrase);
        frasesAluno = lview.findViewById(R.id.frasesAluno);
        radioGroup = lview.findViewById(R.id.escolherJogo);

        lview.findViewById(R.id.comecar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(alunosFrase.isChecked()){

                    radioGroup.clearCheck();
                    ((MainActivity) getActivity ()).replaceAlunosfrase();

                }else if (frasesAluno.isChecked()){

                    radioGroup.clearCheck();
                    ((MainActivity) getActivity ()).replaceFrasesAluno();

                }else{
                    Toast.makeText(getActivity(),"Selecione um jogo!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return lview;
    }

}
