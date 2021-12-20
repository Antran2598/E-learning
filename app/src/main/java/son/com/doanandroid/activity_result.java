package son.com.doanandroid;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import java.util.ArrayList;

import son.com.doanandroid.score.ScoreController;


public class activity_result extends Activity {
    ArrayList<Question> Questions = new ArrayList();
    ImageView imageWin;

    Button Luudiem, BtnBack;
    TextView KQ, tvScore;
    int pos = 0;//vị trí câu hỏi trong danh sách
    int kq = 0; //lưu số câu trả lời đúng

    ScoreController scoreController;
    Animation animation;
    MediaPlayer SoundBackground = new MediaPlayer();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreController = new ScoreController(activity_result.this);
        SoundBackground = MediaPlayer.create(activity_result.this, R.raw.winner);
        SoundBackground.start();


        final Intent intent = getIntent();
        Questions = (ArrayList<Question>) intent.getExtras().getSerializable("Questions");
        begin();


        Intent callerIntent = getIntent();
        KQ = findViewById(R.id.TxtKQ);

        final Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ") + "/" + packageFromCaller.getInt("Socau"));






        BtnBack = findViewById(R.id.BtnBack);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_result.this, activity_trangchu.class);
                startActivity(intent);

            }
        });






        Luudiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity_result.this);
                LayoutInflater inflater = activity_result.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.activity_savescore, null);
                builder.setView(view);


                final EditText edtName= (EditText) view.findViewById(R.id.edtHoTen);
                //   final EditText edtMSSV= (EditText) view.findViewById(R.id.edtMssv);
                final TextView tvScore= (TextView) view.findViewById(R.id.tvScore);
                final EditText edtRoom = (EditText) view.findViewById(R.id.edtEmail);

                tvScore.setText(packageFromCaller.getInt("KQ") + "/" + packageFromCaller.getInt("Socau"));
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        String score = tvScore.getText().toString();
                        String room = edtRoom.getText().toString();


                        scoreController.insertScore(name, score, room);
                        Toast.makeText(activity_result.this, "Lưu điểm thành công!", Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog b = builder.create();
                b.show();
            }
        });

/*
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(activity_result.this);
                LayoutInflater inflater=activity_result.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.allert_dialog_save_score,null);
                builder.setView(view);

                final EditText edtName= (EditText) view.findViewById(R.id.edtName);
                final EditText edtRoom= (EditText) view.findViewById(R.id.edtRoom);
                TextView tvScore= (TextView) view.findViewById(R.id.tvScore);
                final int numTotal= numTrue*10;
                tvScore.setText(numTotal+" điểm");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name= edtName.getText().toString();
                        String room= edtRoom.getText().toString();
                        scoreController.insertScore(name,numTotal,room);
                        Toast.makeText(activity_result.this, "Lưu điểm thành công!",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });


                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b= builder.create();
                b.show();
            }
        });
 */
    }


/*
    public void checkResult(){
        for(int i=0; i< Questions.size(); i++){
            if( Questions.get(i).getTraloi().equals("")==true){
                numNoAns++;
            }else if(Questions.get(i).getAnswer().equals(Questions.get(i).getTraloi())==true){
                numTrue++;
            }else numFalse++;
        }
    }


 */



    public void begin() {
        //
       Luudiem = findViewById(R.id.BtnLuuDiem);
        // btnSave=(Button)findViewById(R.id.btnSave);
        tvScore = findViewById(R.id.tvScore);

    }

}

