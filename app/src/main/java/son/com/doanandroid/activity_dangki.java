package son.com.doanandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class activity_dangki extends Activity {
    Button btnDangKy,btnthoat;
    EditText edtEmail, edtPassword;
    FirebaseAuth mAuthencation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        mAuthencation = FirebaseAuth.getInstance();
        Anhxa();


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });

        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void DangKy() {
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        mAuthencation.createUserWithEmailAndPassword(email, pass)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                      Toast.makeText(activity_dangki.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(activity_dangki.this, MainActivity.class);
                      startActivity(intent);

                  } else
                  {
                      Toast.makeText(activity_dangki.this, "That bai", Toast.LENGTH_SHORT).show();

                  }
              }
              });
}


    private void Anhxa() {
        btnDangKy = (Button) findViewById(R.id.buttonDangKy);
        edtEmail = (EditText) findViewById(R.id.editTextemail);
        edtPassword = (EditText) findViewById(R.id.editTextpassword);
        btnthoat= (Button) findViewById(R.id.BtnBack);

    }
}




