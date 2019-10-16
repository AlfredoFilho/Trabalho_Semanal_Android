package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a193532_c195741.ft.unicamp.br.aula03.R;
import a193532_c195741.ft.unicamp.br.aula03.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstatisticasFragment extends Fragment {
    public EstatisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
    }

}

