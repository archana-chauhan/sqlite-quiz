package com.example.archana_pc.newquizui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeScreen extends AppCompatActivity {
    Button playGame,quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        playGame =(Button) findViewById(R.id.playGame);

        getSupportActionBar().hide();
//        quit = (FButton) findViewById(R.id.quit);
//        tQ = (TextView)findViewById(R.id.tQ);
        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this,LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Quit button - This will quit the game
//        quit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        //Typeface - this is for fonts style
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/TitilliumWeb-Bold.ttf");
        playGame.setTypeface(typeface);
//        quit.setTypeface(typeface);
//        tQ.setTypeface(typeface);
    }
}
