package com.example.archana_pc.newquizui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class DifferentClassWiseQuiz extends AppCompatActivity {

    CardView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.different_class_with_different_questions);

        getSupportActionBar().setTitle("Select Class");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn1 = (CardView) findViewById(R.id.cvbtn1);
        btn2 = (CardView) findViewById(R.id.cvbtn2);
        btn3 = (CardView) findViewById(R.id.cvbtn3);
        btn4 = (CardView) findViewById(R.id.cvbtn4);
        btn5 = (CardView) findViewById(R.id.cvbtn5);
        btn6 = (CardView) findViewById(R.id.cvbtn6);
        btn7 = (CardView) findViewById(R.id.cvbtn7);
        btn8 = (CardView) findViewById(R.id.cvbtn8);


        //OnCLick listener to go next que
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getTag().equals("Class1")){




                }


                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });


        //OnCLick listener to go next que
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DifferentClassWiseQuiz.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
