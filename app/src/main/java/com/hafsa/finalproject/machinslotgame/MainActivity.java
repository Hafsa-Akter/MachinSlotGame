package com.hafsa.finalproject.machinslotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hafsa.finalproject.machinslotgame.ImageViewScrolling.IEventEnd;
import com.hafsa.finalproject.machinslotgame.ImageViewScrolling.ImageViewScrolling;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {
    ImageView btn_up,btn_down;

    TextView txt_score;
    ImageViewScrolling image1,image2,image3;

    int count_done=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        btn_up=findViewById(R.id.btn_up);
        btn_down=findViewById(R.id.btn_down);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        txt_score=findViewById(R.id.text_score);

        image1.setEventEnd(MainActivity.this);
        image2.setEventEnd(MainActivity.this);
        image3.setEventEnd(MainActivity.this);

        btn_up.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(Common.score>=50){

                    btn_up.setVisibility(View.GONE);
                    btn_down.setVisibility(View.VISIBLE);

                    image1.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);//5-15 rotate count
                    image2.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);//5-15 rotate count
                    image3.setValueRandom(new Random().nextInt(6),new Random().nextInt((15-5)+1)+5);//5-15 rotate count

                    Common.score-=50;

                    txt_score.setText(String.valueOf(Common.score));


                }else{

                    Toast.makeText(MainActivity.this, "You don't have enough money..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void eventEnd(int result, int count) {
        if(count_done<2){
            count_done++;

        }else {

            btn_down.setVisibility(View.GONE);
            btn_up.setVisibility(View.VISIBLE);
            count_done=0;

            if(image1.getValue()==image2.getValue() && image2.getValue()==image3.getValue()){
                Toast.makeText(MainActivity.this, "You have won big prize", Toast.LENGTH_SHORT).show();

                 Common.score+=300;
                 txt_score.setText("Score : "+String.valueOf(Common.score));

        } else if(image1.getValue()==image2.getValue()||
                  image1.getValue()==image3.getValue()||
                 image2.getValue()==image3.getValue()){
                Toast.makeText(MainActivity.this, "You have won small prize", Toast.LENGTH_SHORT).show();
                Common.score+=150;
                txt_score.setText("Score : "+String.valueOf(Common.score));

        }else{
                Toast.makeText(MainActivity.this, "You lose", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
