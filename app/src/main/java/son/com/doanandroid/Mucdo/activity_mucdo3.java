package son.com.doanandroid.Mucdo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import son.com.doanandroid.Activities.activity_test;
import son.com.doanandroid.R;
import son.com.doanandroid.activity_trangchu;


public class activity_mucdo3 extends Activity {
    Button btnmucdo,Btnde,btntrungbinh,btnkho,Quit;
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuthencation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucdo);
        mAuthencation = FirebaseAuth.getInstance();
        Anhxa();


        Btnde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mucdo3.this,son.com.doanandroid.Activities.activity_test3.class);

                // gan vao name muc co gia tri la De
                intent.putExtra("muc", "De");
                startActivity(intent);
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(activity_mucdo3.this, R.raw.flute);
                mediaPlayer.start();
            }
        });
        btntrungbinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mucdo3.this,son.com.doanandroid.Activities.activity_test3.class);
                intent.putExtra("muc", "TrungBinh");
                startActivity(intent);
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(activity_mucdo3.this, R.raw.bf);
                mediaPlayer.start();
            }
        });
        btnkho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mucdo3.this,son.com.doanandroid.Activities.activity_test3.class);
                intent.putExtra("muc", "Kho");
                startActivity(intent);
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(activity_mucdo3.this, R.raw.ps1cg);
                mediaPlayer.start();
            }
        });
        Quit = (Button) findViewById(R.id.Btntrove);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_mucdo3.this, activity_trangchu.class);
                startActivity(intent);
            }
        });
    }



    private void Anhxa() {

        Btnde = (Button) findViewById(R.id.Btnde);
        btntrungbinh=(Button) findViewById(R.id.Btntrungbinh);
        btnkho =(Button) findViewById(R.id.Btnkho);
    }
}