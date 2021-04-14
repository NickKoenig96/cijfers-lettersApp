package com.example.lettersnumbersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    com.example.lettersnumbersapp.viewModel viewModel;

    LetterFragment letterFragment;
    NumberFragment numberFragment;

ProgressBar pb;
int counter = 0;


    Timer t = new Timer();
    private static int PERIOD = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        viewModel = new ViewModelProvider(this).get(com.example.lettersnumbersapp.viewModel.class);
        letterFragment = new LetterFragment();
        numberFragment = new NumberFragment();


        TextView test = (TextView) findViewById(R.id.test);
            viewModel.getCurrentPlayerRound().observe(this, playerRound -> test.setText(playerRound.toString()));





        TextView player = (TextView) findViewById(R.id.player);

        viewModel.getPlayer().observe(this, playerNumber -> player.setText(playerNumber));


        ImageView taskImage = (ImageView) findViewById(R.id.imageTask);

        viewModel.getFitnessTask().observe(this,
                imageTask -> {

                    switch (imageTask) {
                        case "jump":
                            taskImage.setImageResource(R.drawable.jump);
                            break;
                        case "arm":
                            taskImage.setImageResource(R.drawable.arm);
                            break;
                        case "loser":
                            taskImage.setImageResource(R.drawable.loser);
                            break;
                        case "push":
                            taskImage.setImageResource(R.drawable.push);
                            break;
                        case "rope":
                            taskImage.setImageResource(R.drawable.rope);
                            break;
                        case "sit":
                            taskImage.setImageResource(R.drawable.sit);
                            break;
                        case "toe":
                            taskImage.setImageResource(R.drawable.toe);
                            break;
                        default:
                            break;
                    }


                });


        GridLayout cardGridLayout = findViewById(R.id.gridlayout);

        for (int i = 1; i <= 1; i++) {
            View cardView = getLayoutInflater().inflate(R.layout.number_card, cardGridLayout, false);
            TextView tv = cardView.findViewById(R.id.number_card_text);

            viewModel.getNumbers().observe(this, number ->
                    tv.setText(number.toString()));
            viewModel.getLetters().observe(this, character ->
                    tv.setText(character.toString()));
            cardGridLayout.addView(cardView);
        }

        viewModel.getCurrentRound().observe(this, round -> {
            if (round == com.example.lettersnumbersapp.viewModel.NUMBERS_ROUND) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_a, numberFragment)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_a, letterFragment)
                        .commit();
            }
        });




        TextView roundOver = (TextView) findViewById(R.id.roundOver);
        pb = (ProgressBar)findViewById(R.id.progress_bar);
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            public void run() {
                counter++;
                pb.setProgress(counter);

                if(counter == 120){
                    // t.cancel();
                    roundOver.setText("ROUND OVER");


                }

            }
        };

        t.schedule(tt, 0 ,1000);



    }















    public void nextRound(View v) {
        viewModel.nextRound();
        counter = 0;
        TextView roundOver = (TextView) findViewById(R.id.roundOver);
        roundOver.setText(" ");

    }

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt("counter" , counter);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("counter");

    }





}