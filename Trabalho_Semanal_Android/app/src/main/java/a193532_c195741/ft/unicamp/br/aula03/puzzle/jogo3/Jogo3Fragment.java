package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import  a193532_c195741.ft.unicamp.br.aula03.R;

public class Jogo3Fragment extends Fragment {
    private View lview;
    TextView textView;
    TextView txtResult;

    String nomeCorreto;

    private RadioButton nome1;
    private RadioButton nome2;
    private RadioButton nome3;
    private RadioButton nome4;
    private RadioButton nome5;

    ArrayList<String> listaNomes = new ArrayList<>();


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

        getActivity().setTitle("Jogo 3");

        textView = lview.findViewById(R.id.txtFrase);
        txtResult = lview.findViewById(R.id.resultado);

        nome1 = lview.findViewById(R.id.nome1);
        nome2 = lview.findViewById(R.id.nome2);
        nome3 = lview.findViewById(R.id.nome3);
        nome4 = lview.findViewById(R.id.nome4);
        nome5 = lview.findViewById(R.id.nome5);

        new MyFrasesAsyncTask(this).execute();

        lview.findViewById(R.id.btnChecar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checar();
            }
        });

        lview.findViewById(R.id.btnProx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proximoJogo();
            }
        });

        return lview;
    }

    public void proximoJogo(){

        textView.setText("");
        txtResult.setText("");

        nome1.setText("");
        nome2.setText("");
        nome3.setText("");
        nome4.setText("");
        nome5.setText("");

        listaNomes.clear();

        new MyFrasesAsyncTask(this).execute();
    }

    public void checar(){

        if(nome1.isChecked() && nome1.getText().equals(nomeCorreto)){

            txtResult.setText("Correto!");

        }else if(nome2.isChecked() && nome2.getText().equals(nomeCorreto)){

            txtResult.setText("Correto!");

        }else if(nome3.isChecked() && nome3.getText().equals(nomeCorreto)){

            txtResult.setText("Correto!");

        }else if(nome4.isChecked() && nome4.getText().equals(nomeCorreto)){

            txtResult.setText("Correto!");

        }else if(nome5.isChecked() && nome5.getText().equals(nomeCorreto)){

            txtResult.setText("Correto!");

        }else{
            txtResult.setText("Errou!");
        }
    }

    public void atualizaLayout(JSONObject jsonObject){
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("outros");

            textView.append(jsonObject.getString("frase"));

            nomeCorreto = jsonObject.getString("nome");

            listaNomes.add(jsonObject.getString("nome"));
            listaNomes.add(jsonArray.getString(0));
            listaNomes.add(jsonArray.getString(1));
            listaNomes.add(jsonArray.getString(2));
            listaNomes.add(jsonArray.getString(3));

            System.out.println(listaNomes);

            Collections.shuffle(listaNomes);

            System.out.println(listaNomes);

            nome1.setText(listaNomes.get(0));
            nome2.setText(listaNomes.get(1));
            nome3.setText(listaNomes.get(2));
            nome4.setText(listaNomes.get(3));
            nome5.setText(listaNomes.get(4));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

