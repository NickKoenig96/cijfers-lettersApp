package com.example.lettersnumbersapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class viewModel extends ViewModel {
    private MutableLiveData<ArrayList<Character>> letters;
   private MutableLiveData<ArrayList<Integer>> numbers;

    private MutableLiveData<Integer> currentRound;
    private MutableLiveData<Integer> PlayerRound;
    private MutableLiveData<String> player;
    private MutableLiveData<String> imageTask;

    private MutableLiveData<Integer> counter;







    private final Random random = new Random();
    private static final int MAX_SMALL_INT = 9;
    private static final int[] ALLOWED_NUMBERS = {10, 25, 50, 100};
    public static final int NUMBERS_ROUND = 0;
    public static final int LETTERS_ROUND = 1;
    public  int PLAYER_ROUND = 1;
    public  String Player = "Player1";
    public String Image = "";
    public int gameRound = 1;
    int Counter;



    public MutableLiveData<ArrayList<Character>> getLetters() {
        if (letters == null) {
            letters = new MutableLiveData<ArrayList<Character>>();

            letters.setValue(new ArrayList<Character>());
        }
        return letters;
    }

   public MutableLiveData<ArrayList<Integer>> getNumbers() {
        if (numbers == null) {
           numbers = new MutableLiveData<ArrayList<Integer>>();
            numbers.setValue(new ArrayList<Integer>());

        }
        return numbers;
    }

    public MutableLiveData<Integer> getCurrentRound() {
        if (currentRound == null) {
            currentRound = new MutableLiveData<Integer>(NUMBERS_ROUND);
        }
        return currentRound;
    }

    public MutableLiveData<Integer> getCurrentPlayerRound() {
        if (PlayerRound == null) {
            PlayerRound = new MutableLiveData<Integer>(PLAYER_ROUND);
        }
        return PlayerRound;
    }

    public MutableLiveData<String> getPlayer() {
            player = new MutableLiveData<String>(Player);

        return player;
    }

    public MutableLiveData<String> getFitnessTask() {
        imageTask = new MutableLiveData<String>(Image);

        return imageTask;
    }


    public MutableLiveData<Integer> getTimer(){
        Counter++;
        counter.setValue(Counter);
        counter = new MutableLiveData<Integer>(Counter);
        return counter;

    }









    public void fitnessTask(){
        if(gameRound < 26){
            gameRound++;

            if(gameRound < 5){
                Image = "loser";
                imageTask.setValue(Image);

            }
            if(gameRound == 5){
                Image = "jump";
                imageTask.setValue(Image);


            }

            if(gameRound == 9){
                Image = "arm";
                imageTask.setValue(Image);


            }
            if(gameRound == 13){
                Image = "push";
                imageTask.setValue(Image);

            }
            if(gameRound == 17){
                Image = "rope";
                imageTask.setValue(Image);


            }
            if(gameRound == 21){
                Image = "sit";
                imageTask.setValue(Image);


            }
            if(gameRound == 25){
                Image = "toe";
                imageTask.setValue(Image);


            }
            if(gameRound >25){
                gameRound = 5;
            }


        }else{
            gameRound = 0;
        }




}



public void player(){
        if(PLAYER_ROUND < 3){
            Player = "Player1";
            player.setValue(Player);

        }else{
            Player = "Player2";
            player.setValue(Player);

        }
}

    public  void playerRound(){
        if(PLAYER_ROUND <= 3){
            PLAYER_ROUND++;
            PlayerRound.setValue(PLAYER_ROUND);
        }else{
            PLAYER_ROUND = 1;
            PlayerRound.setValue(PLAYER_ROUND);


        }
    }




    public void nextRound() {
        clearLetters();
       clearNumbers();
       playerRound();
        player();
        fitnessTask();





//        currentRound.setValue(1 - currentRound.getValue());

        if (currentRound.getValue() == NUMBERS_ROUND)
            currentRound.setValue(LETTERS_ROUND);
        else
            currentRound.setValue(NUMBERS_ROUND);




    }

    public void pickSmallNumber() {
        ArrayList<Integer> list = getNumbers().getValue();
        list.add(random.nextInt(MAX_SMALL_INT) + 1);
        numbers.setValue(list);
    }

    public void pickLargeNumber() {
        ArrayList<Integer> list = getNumbers().getValue();
        int number = ALLOWED_NUMBERS[random.nextInt(ALLOWED_NUMBERS.length - 1)];
        list.add(number);
        numbers.setValue(list);
    }

    public void pickVowel() {
        ArrayList<Character> list = getLetters().getValue();
        char c;
        do {
            c = pickALetter();
        } while (!isVowel(c));
        list.add(c);
        letters.setValue(list);
    }

    public void pickConsonant() {
        ArrayList<Character> list = getLetters().getValue();
        char c;
        do {
            c = pickALetter();
        } while (!isConsonant(c));
        list.add(c);
        letters.setValue(list);
    }

    public void clearLetters() {
        ArrayList<Character> list = getLetters().getValue();
        list.clear();
        letters.setValue(list);
    }

    public void clearNumbers() {
        ArrayList<Integer> list = getNumbers().getValue();
        list.clear();
        numbers.setValue(list);
    }


    private char pickALetter() {
        int ascii = random.nextInt(26) + 65;; // lowercase 'a'
        return (char)ascii;
    }

    private boolean isVowel (char c) {
        char[] vowels = {'A', 'E', 'I', 'O', 'U'};

        for (char v: vowels) {
            if (v == c) return true;
        }
        return false;
    }

    private boolean isConsonant (char c) {
        return !isVowel(c);
    }

}
