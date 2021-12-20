package son.com.doanandroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import son.com.doanandroid.Mucdo.activity_mucdo;

public class activity_trangchu extends MainActivity {
    Button diem, Quit,batdau,Rank,Correct;
    ImageView imgRotate, imgtranslate;
    Animation animation;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        diem = (Button) findViewById(R .id.Btndiemnguoichoi);
        diem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, activity_highscore.class);
                startActivity(intent);
                mediaPlayer = MediaPlayer.create(activity_trangchu.this, R.raw.gun);
                mediaPlayer.start();
            }
        });
        Quit = (Button) findViewById(R.id.BtnThoat);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, MainActivity.class);
                startActivity(intent);
                mediaPlayer = MediaPlayer.create(activity_trangchu.this, R.raw.gun);
                mediaPlayer.start();
            }
        });


        Correct = (Button) findViewById(R.id.Btncorrect);
        Correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, CorrectWord.class);
                startActivity(intent);

            }
        });


        Rank = (Button) findViewById(R.id.Rank);
        Rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, SaveScore.class);
                startActivity(intent);
            }
        });


        batdau = (Button) findViewById(R.id.Btnbatdau);
        batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_trangchu.this, choncau.class);
                startActivity(intent);
                mediaPlayer = MediaPlayer.create(activity_trangchu.this, R.raw.punch);
                mediaPlayer.start();
            }
        });
        ImageView imgRotate, imgtranslate;
        imgRotate = (ImageView) findViewById(R.id.imageViewRotate);

        animation = AnimationUtils.loadAnimation(this,R.anim.animin_rotate);
        imgRotate.startAnimation(animation);
        imgtranslate=(ImageView)findViewById(R.id.imageView2);
        animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        imgtranslate.startAnimation(animation);

    }
}
