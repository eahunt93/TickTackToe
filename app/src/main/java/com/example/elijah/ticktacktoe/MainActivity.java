package com.example.elijah.ticktacktoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 equals yellow and 1 is red
    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5}, {6,7,8}, {0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,6,6}};



    public void playagainbutton(View view){

        gameIsActive =true;

        LinearLayout layout =(LinearLayout)findViewById(R.id.playagain);
        layout.setVisibility(View.INVISIBLE);



        activePlayer = 0;

        for(int i = 0; i < gameState.length;i++){
            gameState[i] = 2;
        }
        GridLayout board = (GridLayout)findViewById(R.id.board);

        for(int i = 0; i< board.getChildCount(); i++){
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }


    }


    public void dropin(View view){
        ImageView counter = (ImageView) view;

        counter.getTag().toString();

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {


                counter.setImageResource(R.drawable.x);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPositions :winningPositions){
                if(gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]]==gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2){
                    System.out.println(gameState[winningPositions[0]]);


                    //Someone has won
                    String winner = "O";

                    gameIsActive = false;

                    if(gameState[winningPositions[0]] == 0){
                        winner = "X";
                    }

                    TextView winnermessage = (TextView)findViewById(R.id.winnermessage);
                    winnermessage.setText(winner + " Has won!");

                    LinearLayout playagain = (LinearLayout)findViewById(R.id.playagain);
                    playagain.setVisibility(view.VISIBLE);


                }else{
                    boolean gameIsOver = true;

                    for(int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }

                        if(gameIsOver){
                            TextView winnermessage = (TextView)findViewById(R.id.winnermessage);
                            winnermessage.setText("Cats game!!!");

                            LinearLayout playagain = (LinearLayout)findViewById(R.id.playagain);
                            playagain.setVisibility(view.VISIBLE);
                        }

                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
