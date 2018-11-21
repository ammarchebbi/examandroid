package exam.esprit.tn.examenandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView txtNom, txtDescription;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtDescription = findViewById(R.id.txtDescription);
        txtNom = findViewById(R.id.txtNom);
        btnDelete = findViewById(R.id.btnDelete);

        txtNom.setText(ListFragment.dataModel.getNom());
        txtDescription.setText(ListFragment.dataModel.getDescription());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);
                alertDialog.setMessage("Voules vous vraiment supprimer " + txtNom.getText().toString());
                alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                        startActivity(new Intent(DetailActivity.this, MainActivity.class));

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(DetailActivity.this, MainActivity.class));
                    }
                });
                alertDialog.show();
            }
        });

    }

    private void delete() {
        final DatabaseHelper databaseHelper = new DatabaseHelper(DetailActivity.this);
        databaseHelper.deleteFilm(ListFragment.dataModel);
    }
}
