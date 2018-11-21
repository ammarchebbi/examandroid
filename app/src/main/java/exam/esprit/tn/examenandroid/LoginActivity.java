package exam.esprit.tn.examenandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.file.Files;

public class LoginActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUsername.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")) {
                    insertInShared();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
        readFromShared();
    }

    private void insertInShared() {
        SharedPreferences.Editor editor = getSharedPreferences("default", MODE_PRIVATE).edit();
        editor.putString("username", "esprit");
        editor.putString("password", "123");
        editor.apply();
    }

    private void readFromShared() {
        SharedPreferences prefs = getSharedPreferences("default", MODE_PRIVATE);
        String usermanetext = prefs.getString("username", null);
        String passwordtext = prefs.getString("password", null);

        if (usermanetext != null && passwordtext != null) {
            if (usermanetext.equals("esprit") && passwordtext.equals("123"))
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}
