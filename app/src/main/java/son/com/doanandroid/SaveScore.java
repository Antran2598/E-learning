package son.com.doanandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import son.com.doanandroid.score.Score;

public class SaveScore extends AppCompatActivity {

    private SQLiteDatabase db;
    private EditText editName, editPhone;
    private Button btnback;

    private ListView lvUser;
    private ArrayAdapter<Score> adapter;
    private ArrayList<Score> scoreList = new ArrayList<>();

    int idUpdate = -1;
    int HighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_score);

        initData();
        btnback = findViewById(R.id.BtnThoat);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveScore.this,activity_trangchu.class);
                startActivity(intent);

            }
        });

        lvUser = findViewById(R.id.lvUser);
        adapter = new ArrayAdapter<Score>(this, 0, scoreList) {

            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_list_score, null);

                TextView tvName = convertView.findViewById(R.id.tvNameStudent);
                TextView tvPhone = convertView.findViewById(R.id.tvScore);

                Score u = scoreList.get(position);
                tvName.setText(u.getName());
                tvPhone.setText(u.getScore());

                return convertView;
            }
        };
        lvUser.setAdapter(adapter);


        loadData();
    }

    private void showInfo(int position){
        Score u = scoreList.get(position);
        editName.setText(u.getName());
        editPhone.setText(u.getScore());

        idUpdate = u.getId();
    }

    private void insertRow() {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String sql = "INSERT INTO tbscore (name, phone) VALUES ('" + name + "','" + phone + "')";
        db.execSQL(sql);
    }

    private void updateRow(){
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String sql = "UPDATE tbscore SET name = '" + name + "', phone = '" + phone + "' WHERE id = " + idUpdate;
        db.execSQL(sql);
    }

    private void deleteUser(int position){
        int id = scoreList.get(position).getId();
        String sql = "DELETE FROM tbscore WHERE id = " + id;
        db.execSQL(sql);
    }

    private void initData() {
        db = openOrCreateDatabase("tracnghiem.db", MODE_PRIVATE, null);

        String sql = "CREATE TABLE IF NOT EXISTS tbchuno (id integer primary key autoincrement, name text, phone text)";
        db.execSQL(sql);
    }


    private void loadData() {
        scoreList.clear();

        String sql = "SELECT * FROM tbscore";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);

            Score u = new Score();
            u.setId(id);
            u.setName(name);
            u.setScore(phone);

            scoreList.add(u);

            cursor.moveToNext();
        }

        adapter.notifyDataSetChanged();
    }


}