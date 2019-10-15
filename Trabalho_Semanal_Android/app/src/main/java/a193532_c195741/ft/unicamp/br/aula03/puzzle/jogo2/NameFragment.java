package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo2;


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
import java.util.Random;
import a193532_c195741.ft.unicamp.br.aula03.alunos.Aluno;
import a193532_c195741.ft.unicamp.br.aula03.alunos.Alunos;
import a193532_c195741.ft.unicamp.br.aula03.R;
import java.util.HashMap;
import java.util.Map;

import a193532_c195741.ft.unicamp.br.aula03.alunos.AlunosFragment.OnBiografiaRequest;

public class NameFragment extends Fragment {

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
                                            System.out.println("Porcentagem: " + porcentAmostral);

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

     /*
        mapAlunoMaisErrado.keySet().toArray()[0] #Primeiro key
        mapAlunoMaisErrado.values().toArray()[0] #Primeiro value

        mapAlunoBotaoMaisErrado.keySet().toArray()[0] #Primeiro key
        mapAlunoBotaoMaisErrado.values().toArray()[0] #Primeiro value
    */

    System.out.println("-------------------------------------------" +
                "\nAluno mais errado (imagem): " + mapAlunoMaisErrado.keySet().toArray()[0] +
                "\nAluno mais errado (botão): " + mapAlunoBotaoMaisErrado.keySet().toArray()[0]);
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

}