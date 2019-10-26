package a193532_c195741.ft.unicamp.br.aula03.internet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import  a193532_c195741.ft.unicamp.br.aula03.R;


public class InternetFragment extends Fragment {
    private View lview;



    public InternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_internet, container, false);
        }

        final TextView textView = lview.findViewById(R.id.textView);


        lview.findViewById(R.id.btnViaCep).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url ="https://trabalhosemanal-f0ef7.firebaseio.com/.json";

                        Log.i("InternetFragment",url);


                        String json = "{\"Id\":1,\"Nome\":\"Cleofas\",\"Telefone\":19982314318}";

                        new MyFirstWebService(textView).execute(url,json);
                    }
                }
        );
        return lview;
    }
}
