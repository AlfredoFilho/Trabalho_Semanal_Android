package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3.estatisticas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import a193532_c195741.ft.unicamp.br.aula03.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstatAlunosFrase extends Fragment {

    TextView textView;
    private View lview;

    public EstatAlunosFrase() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_estat_alunos_frase, container, false);
        }

        textView = lview.findViewById(R.id.txtEstAlunos);

        String comando = "GET";
        String urlGet = "https://estatisticasjogo3.firebaseio.com/Database/Jogo_AlunosFrase/.json";

        new EstFirebaseJogo3Frases(this).execute(urlGet, comando);

        return lview;

    }

    public void atualizaLayoult(JSONObject jsonObject) throws JSONException {

        String stringEstatisticas = "";
        JSONArray keys = jsonObject.names ();
        int porcAcertos;
        int porcErros;

        for (int i = 0; i < keys.length (); ++i) {

            String key = keys.getString (i);
            if(!key.equals("DefaultInsert")){
                JSONObject values = new JSONObject(jsonObject.getString (key));

                int acertos = values.getInt("Acertos");
                int erros = values.getInt("Erros");

                if(acertos == 0){

                    porcAcertos = 0;
                    porcErros = 100;

                }else if(erros == 0){

                    porcAcertos = 100;
                    porcErros = 0;

                }else{

                    porcAcertos = (100 * acertos) / (acertos + erros);
                    porcErros = 100 - porcAcertos;

                }

                stringEstatisticas = stringEstatisticas + "<b>" + key + "</b>" + ": Acertos: <b>" + porcAcertos + "</b>% Erros: <b>" + porcErros + "</b>% <br/><br/>";

            }

        }
        textView.setText(Html.fromHtml(stringEstatisticas));

    }


}
