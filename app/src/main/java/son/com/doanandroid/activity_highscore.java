package son.com.doanandroid;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class activity_highscore extends Activity {
    TextView Txt1,Quit;
    int HighScore;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Quit = (Button) findViewById(R.id.Btnquaylai);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_highscore.this, activity_trangchu.class);
                startActivity(intent);
            }
        });
        Txt1 = (TextView)findViewById(R.id.TxtHighscore);
        LoadHighScore();
        Txt1.setText(""+ HighScore);

    }
    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);

        }

    }
}