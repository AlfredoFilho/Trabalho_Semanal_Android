package a193532_c195741.ft.unicamp.br.aula03.internet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import  a193532_c195741.ft.unicamp.br.aula03.R;


public class InternetFragment extends Fragment {
    private View lview;


    public InternetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (lview == null) {
            lview = inflater.inflate(R.layout.fragment_internet, container, false);
        }

        final TextView textView = lview.findViewById(R.id.textView);
        final EditText editText = lview.findViewById(R.id.editText);

        lview.findViewById(R.id.btnViaCep).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new MyViaCepAsyncTask(textView).execute(editText.getText().toString());
                    }
                }
        );


        return lview;
    }

}
