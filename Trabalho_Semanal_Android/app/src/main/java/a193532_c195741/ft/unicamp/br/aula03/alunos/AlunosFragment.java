package a193532_c195741.ft.unicamp.br.aula03.alunos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import a193532_c195741.ft.unicamp.br.aula03.R;

public class AlunosFragment extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    MyFirstAdapter mAdapter;
    private OnReplaceRequeste onReplaceRequeste;
    private OnBiografiaRequest onBiografiaRequest;
    AbrirBiografiaAluno abrirBiografiaAluno;


    public interface AbrirBiografiaAluno {
        public void abrirBiografiaAluno(int position);
    }

    public void setAbrirBiografiaAluno(AbrirBiografiaAluno abrirBiografiaAluno) {
        this.abrirBiografiaAluno = abrirBiografiaAluno;
    }

    public AlunosFragment() {
        // Required empty public constructor
    }

    public interface OnBiografiaRequest {
        void setPosition(int position);
    }

    public void setOnReplaceRequeste(OnReplaceRequeste onReplaceRequeste) {
        this.onReplaceRequeste = onReplaceRequeste;
    }

    public void setMyOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest){
        this.onBiografiaRequest = onBiografiaRequest;
    }



    public interface OnReplaceRequeste{
         void setReplace(int position);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_alunos, container, false);
        }

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new MyFirstAdapter(new ArrayList(Arrays.asList(Alunos.alunos)));
        mAdapter.setMyOnItemClickListener(
                new MyFirstAdapter.MyOnItemClickListener() {
                    @Override
                    public void mostrarNomeAluno(String nome) {
                        Toast.makeText(getActivity(), nome, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void enviarPosicaoAluno(int index) {
                        if (abrirBiografiaAluno != null) {
                            abrirBiografiaAluno.abrirBiografiaAluno(index);
                        }
                    }
                }
        );


        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

}
