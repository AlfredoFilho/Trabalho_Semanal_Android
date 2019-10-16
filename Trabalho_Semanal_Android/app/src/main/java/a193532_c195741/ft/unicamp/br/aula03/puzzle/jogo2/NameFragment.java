package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import a193532_c195741.ft.unicamp.br.aula03.alunos.Aluno;
import a193532_c195741.ft.unicamp.br.aula03.alunos.Alunos;
import a193532_c195741.ft.unicamp.br.aula03.R;
import java.util.HashMap;
import java.util.Map;

import a193532_c195741.ft.unicamp.br.aula03.alunos.AlunosFragment.OnBiografiaRequest;

public class NameFragment extends Fragment {

    private NameDBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private View lview;

    private Random random = new Random();
    private String nomeCorreto;
    private int positionAluno;
    private int numTentativas;
    private float porcentAmostral;
    private float qtdErros;
    private float qtdJogadas;
    private ArrayList<Aluno> alunosList = new ArrayList(Arrays.asList(Alunos.alunos));
    private ImageView imageView;
    private TextView txtTentativas;
    private TextView txtFeedback;
    private ArrayList<Button> arrayListButton;

    Map mapAlunoMaisErrado = new HashMap();
    Map mapAlunoBotaoMaisErrado = new HashMap();

    private OnBiografiaRequest onBiografiaRequest;

    public void setOnBiografiaRequest(OnBiografiaRequest onBiografiaRequest) {
        this.onBiografiaRequest = onBiografiaRequest;
    }

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_name, container, false);
        }

        imageView = lview.findViewById(R.id.imageFoto);
        txtTentativas = lview.findViewById(R.id.txtTentativas);
        txtFeedback = lview.findViewById(R.id.txtFeedback);

        arrayListButton = new ArrayList<>();
        arrayListButton.add((Button) lview.findViewById(R.id.button1));
        arrayListButton.add((Button) lview.findViewById(R.id.button2));
        arrayListButton.add((Button) lview.findViewById(R.id.button3));
        arrayListButton.add((Button) lview.findViewById(R.id.button4));
        arrayListButton.add((Button) lview.findViewById(R.id.button5));
        arrayListButton.add((Button) lview.findViewById(R.id.button6));
        arrayListButton.add((Button) lview.findViewById(R.id.button7));
        arrayListButton.add((Button) lview.findViewById(R.id.button8));
        arrayListButton.add((Button) lview.findViewById(R.id.button9));

        startGame();

        View.OnClickListener guessButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeEscolhido = ((Button) v).getText().toString();
                if (nomeEscolhido.equals( nomeCorreto) ){
                    txtFeedback.setText("Correto!!");
                    new Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    startGame();
                                }
                            }, 1000);
                } else {
                    txtFeedback.setText("Incorreto!!");

                    estatistica(nomeEscolhido, nomeCorreto);

                    numTentativas--;
                    txtTentativas.setText("Tentativas: " + numTentativas);

                    if (numTentativas <= 0) {
                        txtFeedback.setText("VocÃŠ Perdeu!!");

                        new Handler().postDelayed(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        if (onBiografiaRequest != null) {

                                            qtdErros++;
                                            porcentAmostral = qtdErros / qtdJogadas;
                                            onInserirTablePorcAmostral(String.valueOf(porcentAmostral));
                                            onCulsultaPorc();

                                            onBiografiaRequest.setPosition(positionAluno);

                                        }
                                    }
                                }, 1000);


                    }
                }
            }
        };

        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setOnClickListener(guessButtonListener);
        }

        return lview;
    }

    private void estatistica(String nomeEscolhido, String nomeCorreto){

        Boolean value = mapAlunoMaisErrado.containsKey(nomeCorreto);
        if (value == true) {
            Integer aux = (Integer) mapAlunoMaisErrado.get(nomeCorreto);
            mapAlunoMaisErrado.remove(nomeCorreto);
            mapAlunoMaisErrado.put(nomeCorreto, ++aux);
        } else {
            int aux = 1;
            mapAlunoMaisErrado.put(nomeCorreto, aux);
        }

        Boolean value2 = mapAlunoBotaoMaisErrado.containsKey(nomeEscolhido);
        if (value2 == true) {
            Integer qtd = (Integer) mapAlunoBotaoMaisErrado.get(nomeEscolhido);
            mapAlunoBotaoMaisErrado.remove(nomeEscolhido);
            mapAlunoBotaoMaisErrado.put(nomeEscolhido, ++qtd);
        } else {
            int qtd = 1;
            mapAlunoBotaoMaisErrado.put(nomeEscolhido, qtd);
        }

        System.out.println("-----------------------");
        System.out.println("MapAlunoMaisErrado: " + mapAlunoMaisErrado);

        /*Pegar o maior value do MAP, encontrar o key e inserir na tabela*/
        int maxValueInMap = (Integer) (Collections.max(mapAlunoMaisErrado.values()));
        Iterator<Map.Entry<Integer, Integer>> it = mapAlunoMaisErrado.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            if(maxValueInMap == pair.getValue()){

                String nomeMax = String.valueOf(pair.getKey());
                int qtdMax = pair.getValue();

                onInserirTableImg(nomeMax, qtdMax);
            }
        }

        System.out.println("MapAlunoBotaoMaisErrado: " + mapAlunoBotaoMaisErrado);

        /*Pegar o maior value do MAP, encontrar o key e inserir na tabela*/
        int maxValueInMap2 = (Integer) (Collections.max(mapAlunoBotaoMaisErrado.values()));
        Iterator<Map.Entry<Integer, Integer>> it2 = mapAlunoBotaoMaisErrado.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<Integer, Integer> pair = it2.next();
            if(maxValueInMap2 == pair.getValue()){

                String nomeMax = String.valueOf(pair.getKey());
                int qtdMax = pair.getValue();

                onInserirTableBtn(nomeMax, qtdMax);
            }
        }

        onConsultaAB(1);
        onConsultaAB(2);
    }

    private void startGame() {
        qtdJogadas++;
        int guess = random.nextInt(Alunos.alunos.length);
        positionAluno = guess;
        Aluno aluno = Alunos.alunos[guess];
        nomeCorreto = aluno.getNome().split(" ")[0].toLowerCase();
        imageView.setImageResource(aluno.getFoto());
        numTentativas = 3;
        txtTentativas.setText("Tentativas: " + numTentativas);
        txtFeedback.setText("");

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            Aluno candidate = Alunos.alunos[(guess + i) % Alunos.alunos.length];
            arrayList.add(candidate.getNome().split(" ")[0].toLowerCase());
        }
        Collections.shuffle(arrayList);
        for (int i = 0; i < 9; i++) {
            arrayListButton.get(i).setText(arrayList.get(i));
        }
    }

    public void onStart() {
        super.onStart();
        dbHelper = new NameDBHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
        dbHelper.close();
    }

    public void onInserirTableImg(String nome, int qtd) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("qtd", qtd);

        //Apagar toda tabela pro novo insert
        String tabela = "MaisErradoImagem";
        sqLiteDatabase.execSQL("DELETE FROM " + tabela);

        sqLiteDatabase.insert("MaisErradoImagem", null, contentValues);
    }

    public void onInserirTableBtn(String nome, int qtd) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("qtd", qtd);

        //Apagar toda tabela pro novo insert
        String tabela = "MaisErradoBotao";
        sqLiteDatabase.execSQL("DELETE FROM " + tabela);

        sqLiteDatabase.insert("MaisErradoBotao", null, contentValues);
    }

    public void onInserirTablePorcAmostral(String porcent) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("porcent", porcent);

        //Apagar toda tabela pro novo insert
        String tabela = "PorcentAmostral";
        sqLiteDatabase.execSQL("DELETE FROM " + tabela);

        sqLiteDatabase.insert("PorcentAmostral", null, contentValues);
    }

    public void onCulsultaPorc(){
        String tabela = "PorcentAmostral";
        String sql = "Select * from PorcentAmostral";

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String porcentString = cursor.getString(1);
                Float porcentagem = 100 * Float.parseFloat(porcentString);

                System.out.println("CONSULTA "+ tabela + " -> Porcentagem de Erro: " + porcentagem);

            } while (cursor.moveToNext());

        }
        cursor.close();
    }

    public void onConsultaAB(int exerc) {

        String sql = "";
        String tabela = "";

        if(exerc == 1){
            tabela = "MaisErradoImagem";
            sql = "Select * from MaisErradoImagem";

        }else if(exerc == 2){
            tabela = "MaisErradoBotao";
            sql = "Select * from MaisErradoBotao";
        }

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String texto = cursor.getString(1);
                int qtd = cursor.getInt(2);

                System.out.println("CONSULTA "+ tabela + " -> Nome: " + texto + " Qtd: " +qtd);

            } while (cursor.moveToNext());

        }
        cursor.close();
    }
}