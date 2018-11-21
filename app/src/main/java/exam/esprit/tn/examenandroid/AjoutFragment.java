package exam.esprit.tn.examenandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class AjoutFragment extends Fragment {

    Button btnAjout;
    EditText txtNom, txtDescription;
    Spinner spinnerProjection;

    View view;

    public AjoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ajout, container, false);
        txtDescription = view.findViewById(R.id.txtDescription);
        txtNom = view.findViewById(R.id.txtNom);
        spinnerProjection = view.findViewById(R.id.spinnerTypeProjection);
        btnAjout = view.findViewById(R.id.btnAjouter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                Film film = new Film();
                film.setNom(txtNom.getText().toString());
                film.setDescription(txtDescription.getText().toString());
                film.setTypeProjection(spinnerProjection.getSelectedItem().toString());
                databaseHelper.insertFilm(film);

            }
        });
    }
}
