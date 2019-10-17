package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import a193532_c195741.ft.unicamp.br.aula03.R;
import a193532_c195741.ft.unicamp.br.aula03.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstatisticasFragment extends Fragment {

    private NameDBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private TextView textA;
    private TextView textB;
    private TextView textC;
    private TextView textD;
    private TextView textE;
    private View lview;

    public EstatisticasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_estatisticas, container, false);
        }

        textA = lview.findViewById(R.id.textErroImg);
        textB = lview.findViewById(R.id.textErroBtn);
        textC = lview.findViewById(R.id.textPorcent);
        textD = lview.findViewById(R.id.textJogadas);
        textE = lview.findViewById(R.id.textErros);

        return lview;
    }

    public void onCulsultaPorc(){

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String sql = "Select * from PorcentAmostral";
        Float acerto = 0.0f;
        Float erro = 0.0f;
        Float porcentagem = 0.0f;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                acerto = cursor.getFloat(1);
                erro = cursor.getFloat(2);

            } while (cursor.moveToNext());

        }
        cursor.close();

        porcentagem = (erro * 100) / acerto;

        textC.setText(String.valueOf(df.format(porcentagem)) + "%");
        textD.setText(String.valueOf(Math.round(acerto)));
        textE.setText(String.valueOf(Math.round(erro)));
    }

    public void onConsultaA() {

        String sql = "Select * from MaisErradoImagem";
        String texto = "";
        int qtd = 0;
        int temp = 0;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                qtd = cursor.getInt(2);
                if(qtd < temp){
                    ;
                }else{
                    texto = cursor.getString(1);
                    qtd = cursor.getInt(2);
                    temp = qtd;
                }
                qtd = temp;
            } while (cursor.moveToNext());

            String textoFirstUpper = texto.substring(0, 1).toUpperCase() + texto.substring(1);
            textA.setText(textoFirstUpper + " - " + qtd + "x");

        }
        cursor.close();
    }

    public void onConsultaB() {

        String sql = "Select * from MaisErradoBotao";
        String texto = "";
        int qtd = 0;
        int temp = 0;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                System.out.println(qtd);
                qtd = cursor.getInt(2);
                if(qtd < temp){
                    ;
                }else{
                    texto = cursor.getString(1);
                    temp = qtd;
                }
            } while (cursor.moveToNext());
            qtd = temp;
            String textoFirstUpper = texto.substring(0, 1).toUpperCase() + texto.substring(1);
            textB.setText(textoFirstUpper + " - " + qtd + "x");
        }
        cursor.close();
    }


    public void onStart() {
        super.onStart();
        dbHelper = new NameDBHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();

        onConsultaA();
        onConsultaB();
        onCulsultaPorc();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

}

