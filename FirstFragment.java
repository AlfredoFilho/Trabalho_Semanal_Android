package a193532_c195741.ft.unicamp.br.aula03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a193532_c195741.ft.unicamp.br.aula03.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    View view;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

}
