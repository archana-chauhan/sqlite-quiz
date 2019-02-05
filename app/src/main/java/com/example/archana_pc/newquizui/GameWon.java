package com.example.archana_pc.newquizui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.droidbyme.dialoglib.DroidDialog;


public class GameWon extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
//
//        new MaterialStyledDialog.Builder(this)
//                .setTitle("Awesome!")
//                .setDescription("What can we improve? Your feedback is always welcome.")
//                .withDialogAnimation(true)
//                //.withDialogAnimation(true, Duration.SLOW)
//                .show();

    }

    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    public void PlayAgain(View view) {


        Intent intent = new Intent(GameWon.this, MainActivity.class);
        startActivity(intent);
        finish();

//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                if (!isFinishing()){
//                    new DroidDialog.Builder(GameWon.this)
//                            .icon(R.drawable.ic_action_tick)
//                            .title("Fun Fact : ")
//                            .content("Wrong do point avoid by fruit learn or in death. So passage however besides invited comfort elderly be me. Walls began of child civil am heard hoped my. Satisfied pretended mr on do determine by. Old post took and ask seen fact rich. Man entrance settling believed eat joy. Money as drift begin on to. Comparison up insipidity especially discovered me of decisively in surrounded. Points six way enough she its father. Folly sex downs tears ham green forty. ")
//                            .cancelable(true, true)
//                            .positiveButton("ok", new DroidDialog.onPositiveListener() {
//                                @Override
//                                public void onPositive(Dialog droidDialog) {
//                                    Toast.makeText(getApplicationContext(), "YES", Toast.LENGTH_SHORT).show();
//
//
//                                }
//                            })
//                            .negativeButton("NO", new DroidDialog.onNegativeListener() {
//                                @Override
//                                public void onNegative(Dialog droidDialog) {
//                                    Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .show();
//                }
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

