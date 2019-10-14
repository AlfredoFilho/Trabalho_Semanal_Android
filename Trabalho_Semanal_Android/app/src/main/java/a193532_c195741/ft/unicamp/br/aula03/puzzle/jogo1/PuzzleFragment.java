package a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo1;

import a193532_c195741.ft.unicamp.br.aula03.R;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo1.Board;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo1.Boards;
import a193532_c195741.ft.unicamp.br.aula03.puzzle.jogo1.EmptyPuzzle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;


public class PuzzleFragment extends Fragment {

    View view;
    EmptyPuzzle puzzle;
    Spinner gridSpinner;

    public PuzzleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Jogo 1");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_puzzle, container, false);

            gridSpinner = view.findViewById(R.id.grid_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.grid_options, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gridSpinner.setAdapter(adapter);

            view.findViewById(R.id.new_grid_button).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    LinearLayout gridLayout = view.findViewById(R.id.grid_layout);
                    gridLayout.removeAllViews();

                    if (gridSpinner.getSelectedItem().toString().equals("Gislaine")) {
                        startPuzzle(0, view);
                    } else {
                        startPuzzle(1, view);
                    }
                }
            });



            startPuzzle(0, view);
        }

        return view;
    }

    private void startPuzzle(int puzzle, View view) {
        Board board = Boards.getPuzzle(puzzle);
        ArrayList<ImageView> imageViews = new ArrayList();

        LinearLayout puzzleLayout = view.findViewById(R.id.grid_layout);

        for (int i = 0; i < board.getNumLines(); i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j< board.getNumColumns(); j++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(board.getWidth(), board.getHeight()));

                row.addView(imageView);
                imageViews.add(imageView);
            }
            puzzleLayout.addView(row);
        }

        this.puzzle = new EmptyPuzzle(board, imageViews);

    }

}