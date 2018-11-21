package exam.esprit.tn.examenandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut = findViewById(R.id.btnLogOut);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutAjout, new AjoutFragment()).commit();  // display fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutList, new ListFragment()).commit();  // display fragment

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInShared();
            }
        });
    }


    private void deleteInShared() {
        SharedPreferences.Editor editor = getSharedPreferences("default", MODE_PRIVATE).edit();
        editor.remove("username");
        editor.remove("password");
        editor.apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
