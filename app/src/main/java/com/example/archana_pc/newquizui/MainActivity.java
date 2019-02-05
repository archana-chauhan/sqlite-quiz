package com.example.archana_pc.newquizui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.droidbyme.dialoglib.AnimUtils;
import com.droidbyme.dialoglib.DroidDialog;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
//
//import nl.dionsegijn.konfetti.KonfettiView;
//import nl.dionsegijn.konfetti.models.Shape;
//import nl.dionsegijn.konfetti.models.Size;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {


    CountDownTimer countDownTimer;
    TextView timeText, resultText;
    int timeValue = 60;

    Button buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, coinText;
    ComputerRedefineHelper computerRedefineHelper;
    ComputerRedefineQuestions currentQuestion;
    List<ComputerRedefineQuestions> list;
    List<ComputerRedefineQuestions1> list_1;

    int qid = 0;
    int coinValue = 0;
    Typeface tb, sb;
    Button btnShare;

//    KonfettiView viewKonfetti;

    File imagePath;
    Button btnHint, btnSkip;
    TextView tvRange1, tvRange2;
    private Dialog dialog; // class variable

    String strRange1, strRange2;
    int width, height;
    int i = 1;

    private static final int PERMISSION_REQUEST_CODE = 200;

    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());


        getSupportActionBar().setTitle("Computer Redefine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Initializing variables
        questionText = (TextView) findViewById(R.id.triviaQuestion);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        triviaQuizText = (TextView) findViewById(R.id.triviaQuestion);
        timeText = (TextView) findViewById(R.id.timetext);
        resultText = (TextView) findViewById(R.id.resultText);
//        coinText = (TextView) findViewById(R.id.coinText);
        btnShare = (Button) findViewById(R.id.share);

        tvRange1 = findViewById(R.id.tvRange1);
        tvRange2 = findViewById(R.id.tvRange3);

        //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
//        coinText.setTypeface(tb);

        //Our database helper class
        computerRedefineHelper = new ComputerRedefineHelper(this);
        //Make db writable
        computerRedefineHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (computerRedefineHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            computerRedefineHelper.allQuestion();
        }


        if (computerRedefineHelper.getAllOfTheQuestions_1().size() == 0) {
            //If not added then add the ques,options in table
            computerRedefineHelper.allQuestion_1();
        }

        //This will return us a list of data type TriviaQuestion
        list = computerRedefineHelper.getAllOfTheQuestions();

        list_1 = computerRedefineHelper.getAllOfTheQuestions_1();

        Log.d("archana", "List Size: " + String.valueOf(list_1.size()) + list_1.get(i).getQuestion());

        Log.d("archana", String.valueOf(list.size()));

        strRange2 = String.valueOf(list.size());

        tvRange2.setText(strRange2);
//        Collections.sort(list);

//        Iterator<ComputerRedefineQuestions> i = list.iterator();
//        ComputerRedefineQuestions z = i.next();
//
//        Log.d("archana  hbjhghgfg", z.getId());
//
//        for(int i:list)
//            Log.d("archana", list.get(i).getId());
//



//        list.forEach(name -> {
//            Log.d("archana",name.getId());
//        });

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list);



//        Collections.sort(list, new Comparator<ComputerRedefineQuestions>() {
//            public int compare(ComputerRedefineQuestions o1, ComputerRedefineQuestions o2) {
//
//                return o1.getId().compareTo(o2.getId());
//            }
//        });

        //currentQuestion will hold the que, 4 option and ans for particular id
         currentQuestion = list.get(qid);

        Log.d("archana -------", String.valueOf(currentQuestion.getId()));

        strRange1 = String.valueOf(currentQuestion.getId());

        tvRange1.setText(strRange1);

        //countDownTimer
        countDownTimer = new CountDownTimer(62000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();

        btnShare = (Button)findViewById(R.id.share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("archana", "clicked button share");

                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();

//                Bitmap bitmap = takeScreenshot();
//                saveBitmap(bitmap);
//                Bitmap bitmap = takeScreenshot();
//                saveBitmap(bitmap);

            }
        });

        if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }

        btnHint = (Button)findViewById(R.id.hint);
        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {

                    Log.d("archana1", currentQuestion.getOptA());
                    buttonB.setVisibility(View.INVISIBLE);
                    buttonC.setVisibility(View.INVISIBLE);



                }
                else if(currentQuestion.getOptB().equals(currentQuestion.getAnswer())){

                    Log.d("archana2", currentQuestion.getOptB());

                    buttonC.setVisibility(View.INVISIBLE);
                    buttonD.setVisibility(View.INVISIBLE);

                }
                else if(currentQuestion.getOptC().equals(currentQuestion.getAnswer())){

                    Log.d("archana3", currentQuestion.getOptC());
                    buttonD.setVisibility(View.INVISIBLE);
                    buttonA.setVisibility(View.INVISIBLE);



                }
                else if(currentQuestion.getOptD().equals(currentQuestion.getAnswer())){

                    Log.d("archana4", currentQuestion.getOptD());

                    buttonA.setVisibility(View.INVISIBLE);
                    buttonB.setVisibility(View.INVISIBLE);


                }
                else {
                    Log.d("archana5", "Invalid Answer");
                }
            }
        });


        dialog = new Dialog(MainActivity.this);  // always give context of activity.
        dialog.setContentView(R.layout.layout_full_screen_dialog);



        btnSkip = (Button) findViewById(R.id.skip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");

                ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sad, getApplicationContext().getTheme()));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sad));
                }




                dialog2.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        skipDialog();
                    }
                }, 3000); // 3000 milliseconds delay


//
//
//                dialog2 = new Dialog(MainActivity.this);  // always give context of activity.
//                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);

//                skipDialog();
//                Toast.makeText(getApplicationContext(), "Skip Button", Toast.LENGTH_LONG).show();
            }

        });

        //This method will set the que and four options
        updateQueAndOptions();


    }

    private void skipDialog()
    {

            dialog.setContentView(R.layout.layout_full_screen_dialog);

            dialog.setCancelable(false);

//        dialog.show();


        Button dialogButton = (Button) dialog.findViewById(R.id.button_close);
        Button continueButton = dialog.findViewById(R.id.btnContinue);

        TextView tvInfo = dialog.findViewById(R.id.tvInfo);

        TextView tvAnswerText = dialog.findViewById(R.id.tvAnswerText);

        tvAnswerText.setText(currentQuestion.getAnswer());

        tvInfo.setText("Question Skipped");

        RelativeLayout relativeLayout = dialog.findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                finish();

            }
        });


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }



//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        timeText = findViewById(R.id.timetext);
//
//        countDownTimer = new CountDownTimer(22000, 1000) {
//            public void onTick(long millisUntilFinished) {
//
//                //here you can have your logic to set text to timeText
//                timeText.setText(String.valueOf(timeValue) + "\"");
//
//                //With each iteration decrement the time by 1 sec
//                timeValue -= 1;
//
//                //This means the user is out of time so onFinished will called after this iteration
//                if (timeValue == -1) {
//
//                    //Since user is out of time setText as time up
//                    resultText.setText(getString(R.string.timeup));
//
//                    //Since user is out of time he won't be able to click any buttons
//                    //therefore we will disable all four options buttons using this method
//                    disableButton();
//                }
//            }
//
//            //Now user is out of time
//            public void onFinish() {
//                //We will navigate him to the time up activity using below method
//                timeUp();
//            }
//        }.start();
//
//    }
//

    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        countDownTimer.cancel();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        countDownTimer.cancel();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        countDownTimer.start();
//    }

//    public void correctDialog() {
//        final Dialog dialogCorrect = new Dialog(MainActivity.this);
//        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (dialogCorrect.getWindow() != null) {
//            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
//            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
//        }
//        dialogCorrect.setContentView(R.layout.dialog_correct);
//        dialogCorrect.setCancelable(false);
//        dialogCorrect.show();
//
//        //Since the dialog is show to user just pause the timer in background
//        onPause();
//
//
//        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
//        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);
//
//        //Setting type faces
//        correctText.setTypeface(sb);
//        buttonNext.setTypeface(sb);
//
//        //OnCLick listener to go next que
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //This will dismiss the dialog
//                dialogCorrect.dismiss();
//                //it will increment the question number
//                qid++;
//                //get the que and 4 option and store in the currentQuestion
//                currentQuestion = list.get(qid);
//                //Now this method will set the new que and 4 options
//                updateQueAndOptions();
//                //reset the color of buttons back to white
//                resetColor();
//                //Enable button - remember we had disable them when user ans was correct in there particular button methods
//                enableButton();
//
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//
//
//
//            }
//        });
//    }


//    public void timeUp() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }


    public void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
    }

    @Override
    public void onBackPressed() {

        dialog.setContentView(R.layout.layout_quit_dialog);

        dialog.setCancelable(false);

        Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);
        ImageView btnDismiss = dialog.findViewById(R.id.btnClose);


        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                finish();

            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialog.dismiss();
                finish();


            }
        });

        dialog.show();

//        Window window = dialog.getWindow();
//        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
//




//        Intent intent = new Intent(this, HomeScreen.class);
//        startActivity(intent);
//        finish();

//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Quit Or Not");
//        builder.setMessage("Do you want to Quit this? ");
//        builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
////                saveResult();
//                MainActivity.super.onBackPressed();
//            }
//        });

//        builder.setNegativeButton("No", null);
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                MainGameActivity.super.onBackPressed();
//            }
//        });
//        builder.show();
    }



    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache(); }



    private void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/scrnshot.png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }}



    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Playing Computer Redefine Quiz. Please help me out with the solution.";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My High score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));}


    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(getString(R.string.permission_necessary));
                alertBuilder.setMessage(R.string.storage_permission_is_necessary_to_wrote_event);
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openActivity();
                } else {
                    finish();
                }

            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void openActivity() {
        //add your further process after giving permission or to download images from remote server.
    }


    public void updateQueAndOptions() {

        //This method will setText for que and options
        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOptA());
        buttonB.setText(currentQuestion.getOptB());
        buttonC.setText(currentQuestion.getOptC());
        buttonD.setText(currentQuestion.getOptD());


//        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        //set the value of coin text
//        coinText.setText(String.valueOf(coinValue));
        //Now since user has ans correct increment the coinvalue
        coinValue++;

    }

    //Onclick listener for first button
    public void buttonA(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptA().equals(currentQuestion.getAnswer())) {
//            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {

                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
//                disableButton();



                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
                dialog2.setTitle("Pop Up");

                ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy, getApplicationContext().getTheme()));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                }





                dialog2.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        correctDialogFullScreen();
                    }
                }, 3000); // 3000 milliseconds delay


//                showDialog();
                //Show the dialog that ans is correct
//                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWon activity
            else {

                Log.d("archana","gameWon");

                gameWon();

            }
        }
        //User ans is wrong then just navigate him to the PlayAgain activity
        else {
//            showDialog();

            Log.d("archana","gameLostPlayAgain");

            final Dialog dialog2 = new Dialog(MainActivity.this);
            dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
            ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet));
            }

            dialog2.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    gameLostPlayAgainDialogFullScreen();
                }
            }, 3000); // 3000 milliseconds delay

//            gameLostPlayAgainDialogFullScreen();


//            gameLostPlayAgain();

        }
    }

    //Onclick listener for sec button
    public void buttonB(View view) {
        if (currentQuestion.getOptB().equals(currentQuestion.getAnswer())) {
//            buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
//                disableButton();

                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
                ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy, getApplicationContext().getTheme()));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                }



                dialog2.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        correctDialogFullScreen();
                    }
                }, 3000); // 3000 milliseconds delay


                //                showDialog();
//                correctDialog();
            } else {
                Log.d("archana","gameWon");

                gameWon();
            }
        } else {
            Log.d("archana","gameLostPlayAgain");

            final Dialog dialog2 = new Dialog(MainActivity.this);
            dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
            ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet));
            }

            dialog2.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    gameLostPlayAgainDialogFullScreen();
                }
            }, 3000); // 3000 milliseconds delay

        }
    }

    //Onclick listener for third button
    public void buttonC(View view) {
        if (currentQuestion.getOptC().equals(currentQuestion.getAnswer())) {
//            buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
//                disableButton();

                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
                ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy, getApplicationContext().getTheme()));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                }


                dialog2.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        correctDialogFullScreen();
                    }
                }, 3000); // 3000 milliseconds delay


//                showDialog();
//                correctDialog();
            } else {
                Log.d("archana","gameWon");

                gameWon();
            }
        } else {
            Log.d("archana","gameLostPlayAgain");
//            showDialog();
//            Log.d("archana","gameLostPlayAgain");

            final Dialog dialog2 = new Dialog(MainActivity.this);
            dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
            ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet));
            }


            dialog2.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    gameLostPlayAgainDialogFullScreen();
                }
            }, 3000); // 3000 milliseconds delay

//            gameLostPlayAgain();
        }
    }

    //Onclick listener for fourth button
    public void buttonD(View view) {
        if (currentQuestion.getOptD().equals(currentQuestion.getAnswer())) {
//            buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {

                final Dialog dialog2 = new Dialog(MainActivity.this);
                dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
                ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy, getApplicationContext().getTheme()));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                }


                dialog2.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        correctDialogFullScreen();
                    }
                }, 3000); // 3000 milliseconds delay

            } else {
                Log.d("archana","gameWon");
                gameWon();
            }
        } else {

            Log.d("archana","gameLostPlayAgain");

            final Dialog dialog2 = new Dialog(MainActivity.this);
            dialog2.setContentView(R.layout.custom_emoticon_popup_dialog);
//                dialog2.setTitle("Pop Up");
            ImageView imageView = dialog2.findViewById(R.id.ivLogoDialog);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet, getApplicationContext().getTheme()));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.quiet));
            }


            dialog2.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    gameLostPlayAgainDialogFullScreen();
                }
            }, 3000); // 3000 milliseconds delay

        }
    }

    //This method will navigate from current activity to GameWon
    public void gameWon() {
        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }

    //This method is called when user ans is wrong
    //this method will navigate user to the activity PlayAgain
//    public void gameLostPlayAgain() {
//
//        Intent intent = new Intent(getApplicationContext(), PlayAgain.class);
////
////
//        startActivity(intent);
//        finish();
//
//
//    }

    //This method is called when time is up
    //this method will navigate user to the activity Time_Up
    public void timeUp() {
        Intent intent = new Intent(MainActivity.this, Time_Up.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    //This will pause the time
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void correctDialogFullScreen(){

        dialog.setContentView(R.layout.layout_full_screen_dialog);
        dialog.setCancelable(false);
        Button dialogButton = (Button) dialog.findViewById(R.id.button_close);
        Button continueButton = dialog.findViewById(R.id.btnContinue);
        TextView tvInfo = dialog.findViewById(R.id.tvInfo);

        TextView tvText = dialog.findViewById(R.id.tvText);
        TextView tvAnswerText = dialog.findViewById(R.id.tvAnswerText);

        tvText.setVisibility(View.INVISIBLE);
        tvAnswerText.setText("FANTASTIC");

        RelativeLayout relativeLayout = dialog.findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.green));

        tvInfo.setText("Correct Answer");


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                finish();

            }
        });


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void gameLostPlayAgainDialogFullScreen(){

        dialog.setContentView(R.layout.layout_full_screen_dialog);

        dialog.setCancelable(false);



        Button dialogButton = (Button) dialog.findViewById(R.id.button_close);
        Button continueButton = dialog.findViewById(R.id.btnContinue);
        TextView tvInfo = dialog.findViewById(R.id.tvInfo);
        TextView tvAnswerText = dialog.findViewById(R.id.tvAnswerText);

        tvAnswerText.setText(currentQuestion.getAnswer());

        tvInfo.setText("Incorrect Answer");


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(intent);
                finish();

            }
        });


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

}
