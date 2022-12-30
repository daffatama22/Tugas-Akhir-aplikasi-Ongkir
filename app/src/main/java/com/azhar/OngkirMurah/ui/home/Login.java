package com.azhar.OngkirMurah.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azhar.OngkirMurah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    String email, password;
    Button btnLogin,btnRegis;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.Lemail);
        inputPassword = findViewById(R.id.Lpass);
        btnLogin = findViewById((R.id.Llogin));
        btnRegis= findViewById(R.id.Lgugel);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ceklogin();
            }
        });
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegis();
            }
        });



    }
    public void openRegis(){
        Intent intent=new Intent ( this, Registrasi.class);
        startActivity(intent);
    }
    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    private void ceklogin() {

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            reload();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this,
                                    "Login Gagal, cek Email atau Password Anda",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}