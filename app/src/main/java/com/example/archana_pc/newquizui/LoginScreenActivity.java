package com.example.archana_pc.newquizui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    EditText etSchoolId, etPassword;
    Button btnLogin;
    String strLoginId, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getSupportActionBar().hide();

        etSchoolId = findViewById(R.id.etSchoolId);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                strLoginId = etSchoolId.getText().toString();
                strPassword = etPassword.getText().toString();

                if (strLoginId.equalsIgnoreCase("123456") && strPassword.equalsIgnoreCase("1234")) {

                        Intent intent = new Intent(LoginScreenActivity.this, DifferentClassWiseQuiz.class);
                        startActivity(intent);
                }
                else {

                    Toast.makeText(getApplicationContext(), "Please Enter Correct School Id and Password", Toast.LENGTH_LONG).show();
                }
            }

        });


    }
}
