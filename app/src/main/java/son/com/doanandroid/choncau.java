package son.com.doanandroid;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

import androidx.annotation.Nullable;



public class choncau extends Activity {
    public static int cauhoi;
    EditText socauhoi;
    Button bt;
    Button tieptheo;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choncau);
        bt = (Button) findViewById(R.id.BtnBack);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        socauhoi = (EditText) findViewById(R.id.Edtsocau);
        tieptheo=(Button)findViewById(R.id.BtnNext);
        socauhoi.addTextChangedListener(numberTextWathcer);
        tieptheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cauhoi = Integer.parseInt(socauhoi.getText().toString());

                if (cauhoi < 5 || cauhoi >30 ) {
                    socauhoi.setError("Vui lòng nhập số câu 5-10-15-20-25-30");
                    socauhoi.requestFocus();
                    return;
                }
                if (cauhoi == 5){
                    Intent intent = new Intent(choncau.this, son.com.doanandroid.Mucdo.activity_mucdo.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 10){
                    Intent intent = new Intent(choncau.this,  son.com.doanandroid.Mucdo.activity_mucdo1.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 15){
                    Intent intent = new Intent(choncau.this, son.com.doanandroid.Mucdo.activity_mucdo2.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 20){
                    Intent intent = new Intent(choncau.this, son.com.doanandroid.Mucdo.activity_mucdo3.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 25){
                    Intent intent = new Intent(choncau.this, son.com.doanandroid.Mucdo.activity_mucdo4.class);
                    startActivity(intent);
                    finish();
                }
                if (cauhoi == 30){
                    Intent intent = new Intent(choncau.this,son.com.doanandroid.Mucdo.activity_mucdo5.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private TextWatcher numberTextWathcer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String socauhoiInput = socauhoi.getText().toString().trim();
            tieptheo.setEnabled(!socauhoiInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}