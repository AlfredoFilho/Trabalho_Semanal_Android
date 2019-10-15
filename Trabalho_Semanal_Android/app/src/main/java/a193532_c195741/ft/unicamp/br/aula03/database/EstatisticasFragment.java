package a193532_c195741.ft.unicamp.br.aula03.database;


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
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EstatisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
    }
    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();

    }
    public void onInserirTableImg(String nome) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);

        sqLiteDatabase.insert("MaisErradoImagem", null, contentValues);
    }

    public void onInserirTableBtn(String nome) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);

        sqLiteDatabase.insert("MaisErradoBotao", null, contentValues);
    }

    public void onInserirTablePorcAmostral(String porcent) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("porcent", porcent);

        sqLiteDatabase.insert("PorcentAmostral", null, contentValues);
    }


}

