package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import  a193532_c195741.ft.unicamp.br.aula03.R;

public class Jogo3Fragment extends Fragment {
    private View lview;
    TextView textView;
    private ArrayList<Button> arrayListButton;


    public Jogo3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_jogo3, container, false);
        }

        textView = lview.findViewById(R.id.txtFrase);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.btn1));
        arrayListButton.add((Button) lview.findViewById(R.id.btn2));
        arrayListButton.add((Button) lview.findViewById(R.id.btn3));
        arrayListButton.add((Button) lview.findViewById(R.id.btn4));
        arrayListButton.add((Button) lview.findViewById(R.id.btn5));

        new MyFrasesAsyncTask(this).execute();

        return lview;
    }

    public void atualizaLayout(JSONObject jsonObject){
        try {
            textView.append(jsonObject.getString("frase"));
            arrayListButton.get(0).append(jsonObject.getString("nome"));

            /*arrayListButton.get(1).append(jsonObject.getString("nome"));
            arrayListButton.get(2).append(jsonObject.getString("nome"));
            arrayListButton.get(3).append(jsonObject.getString("nome"));
            arrayListButton.get(4).append(jsonObject.getString("nome"));*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

