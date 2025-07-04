package com.example.projectjoke;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageApp,rockImage,paperImage;
    ImageView scissorsImage;


    private int victories = 0;
    private int losses = 0;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imageApp = findViewById(R.id.imgapp);

        rockImage = findViewById(R.id.rock);
        paperImage = findViewById(R.id.paper);
        scissorsImage = findViewById(R.id.scissors);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    public void rock(View view){
      verify("rock");
        System.out.println("Clicou Na pedra");
    }
    public  void paper(View view){
        verify("paper");
        System.out.println("Clicou No papel");
    }
    public void scissors(View view){
        verify("scissors");
        System.out.println("Clicou Na Tesoura");
    }

    public String radomChose(){
        int number = new Random().nextInt(3);
        String[] allThings = {"rock","paper","scissors"};
        String choseApp = allThings[number];

       switch (choseApp) {
           case "rock":
            imageApp.setImageResource(R.drawable.pedra);
               break;
           case "paper":
               imageApp.setImageResource(R.drawable.papel);
               break;
           case "scissors":
               imageApp.setImageResource(R.drawable.tesoura);
               break;
        }
        return choseApp;
    }


    @SuppressLint("SetTextI18n")
    private void verify(String result){
        TextView textResults = findViewById(R.id.results);
        TextView wins = findViewById(R.id.wins);
        TextView losts = findViewById(R.id.numberLost);




     String choices = radomChose();
     if (result == "rock" && choices == "scissors"||
        result == "paper" && choices == "rock"||
        result == "scissors" && choices == "paper"){

        textResults.setText("Você Ganhou!");
        victories++;
        wins.setText(String.valueOf(victories));
        setOpacity();

     }
     else if (result == choices) textResults.setText("Empatou!");
     else {
         textResults.setText("Você Perdeu!");
         losses++;
         losts.setText(String.valueOf(losses));

     }





    }
    public void restart(View view){
        TextView wins = findViewById(R.id.wins);
        TextView losts = findViewById(R.id.numberLost);
        losses = 0;
        victories = 0;
        losts.setText(String.valueOf(losses));
        wins.setText(String.valueOf(victories));

        System.out.println("Clicou no Reset");

    }
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setAlpha(0.5f); // diminui opacidade ao clicar
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.setAlpha(1.0f); // volta ao normal
                    break;
            }
            return false; // return false para continuar tratando como click
        }

    };
    @SuppressLint("ClickableViewAccessibility")
    public void setOpacity(){
        rockImage.setOnTouchListener(touchListener);
        paperImage.setOnTouchListener(touchListener);
        scissorsImage.setOnTouchListener(touchListener);
    }



}