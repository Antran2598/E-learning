package son.com.doanandroid.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import son.com.doanandroid.DBHELPER.DatabaseHelper;
import son.com.doanandroid.DBHELPER.DatabaseHelper1;
import son.com.doanandroid.DBHELPER.DatabaseHelper2;
import son.com.doanandroid.DBHELPER.DatabaseHelper3;
import son.com.doanandroid.DBHELPER.DatabaseHelper4;
import son.com.doanandroid.DBHELPER.DatabaseHelper5;
import son.com.doanandroid.Question;
import son.com.doanandroid.R;
import son.com.doanandroid.activity_checkanser;
import son.com.doanandroid.activity_result;


public class activity_test5 extends Activity {
    private int position = 0;
    private int kq = 0;
    private TextView txtNumAnswer, txtQuestion ,timer;
    private Button btnAnswer,btncheck;
    private RadioGroup rdgAnswer;
    private RadioButton rdbAnswerA, rdbAnswerB, rdbAnswerC, rdbAnswerD;
    public static final String check ="checkanser";
    public int checkanser=0;
    ArrayList<Question> Questions = new ArrayList();
    CounterClass Timer;
    MediaPlayer mediaPlayer;
    ImageView imgIcon;


    //int pos=0;//vị trí câu hỏi trong danh sách
    // kq=0; //lưu số câu trả lời đúng
    //ArrayList<QuestionNare> L = new ArrayList(); //chứa câu hỏi
    int HighScore = 0;
    String muc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
/*
[A,B,C,D]
[4,1,2,3]
*/
        Intent myIntent = getIntent();//cách chuyển ý định tham số android
        muc = myIntent.getStringExtra("muc");

        txtQuestion = (TextView) findViewById(R.id.TxtCauhoi);
        timer = (TextView) findViewById(R.id.timer);
        txtNumAnswer = (TextView)findViewById(R.id.TxtKetqua);
        rdgAnswer = (RadioGroup)findViewById(R.id.RadioGroupTraloi);
        btnAnswer = (Button) findViewById(R.id.BtnTraloi);
        rdbAnswerA= (RadioButton)findViewById(R.id.RdbA);
        rdbAnswerB  = (RadioButton)findViewById(R.id.RdbB);
        rdbAnswerC = (RadioButton)findViewById(R.id.RdbC);
        rdbAnswerD = (RadioButton)findViewById(R.id.RdbD);
        btncheck = (Button)findViewById(R.id.Btncheck);
        imgIcon = findViewById(R.id.ivIcon);


        Timer = new CounterClass(60 * 1000, 1000);
        LoadHighScore();
        ReadDataFromSQL();
        findViewById();

        Display(position);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Timer.start();


        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer();
            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCheck = rdgAnswer.getCheckedRadioButtonId();
                switch (idCheck) {
                    case R.id.RdbA:
//Nếu đáp án là câu A thì cộng kq lên 1
                        if (Questions.get(position).Answer.compareTo("A") == 0) kq = kq + 1;
                        break;
                    case R.id.RdbB:
//Nếu đáp án là câu B thì cộng kq lên 1

                        if (Questions.get(position).Answer.compareTo("B") == 0) kq = kq + 1;

                        break;
                    case R.id.RdbC:
//Nếu đáp án là câu C thì cộng kq lên 1

                        if (Questions.get(position).Answer.compareTo("C") == 0) kq = kq + 1;

                        break;
                    case R.id.RdbD:
//Nếu đáp án là câu D thì cộng kq lên 1

                        if (Questions.get(position).Answer.compareTo("D") == 0) kq = kq + 1;

                        break;

                }
                position++;

//Nếu trả lời hết câu hỏi
                if (position >= Questions.size()) {
                    Intent intent = new Intent(activity_test5.this, activity_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("Socau", position);
                    intent.putExtra("MyPackage", bundle);


                    startActivity(intent);
                    if (kq > HighScore) {
                        HighScore = kq;
                        SaveHighScore();
                        //   mediaPlayer.stop();
                    }
                    mediaPlayer.stop();


                    finish();
                    //
                } else Display(position); //Hiển thị câu hỏi kế tiếp

            }

        });

    }

    /*
    [
        0 => question1,
        1 => question2,
        2 => question3,
        ...
        14 => question14
    ]
    */
    //Hiển thị nội dung
    void Display(int index){
        try{
            Question question = Questions.get(index);

            txtQuestion.setText(question.Question);

            rdbAnswerA.setText(question.AnswerA);
            rdbAnswerB.setText(question.AnswerB);
            rdbAnswerC.setText(question.AnswerC);
            rdbAnswerD.setText(question.AnswerD);

           /*
            txtQuestion.setText(getItem(position).Question);
            rdbAnswerA.setText(getItem(position).AnswerA);
            rdbAnswerB.setText(getItem(position).AnswerB);
            rdbAnswerC.setText(getItem(position).AnswerC);
            rdbAnswerD.setText(getItem(position).AnswerD);

            */
            rdgAnswer.clearCheck(); //xóa checked
            imgIcon.setImageResource(getResources().getIdentifier(getItem(position).getImage() + "", "drawable", "son.com.doanandroid"));
            txtNumAnswer.setText("Câu đúng:" + kq);

        }catch (Exception exception){
            String msg = "Khong co cau hoi.";
            txtQuestion.setText(msg);
            rdbAnswerA.setVisibility(View.GONE);
            rdbAnswerB.setVisibility(View.GONE);
            rdbAnswerC.setVisibility(View.GONE);
            rdbAnswerD.setVisibility(View.GONE);
            Toast.makeText(this, "Error occur", Toast.LENGTH_SHORT).show();
            Log.d("xxx", "Loi ne: "+exception.getMessage()) ;

        }
        if(checkanser!=0){
            rdbAnswerA.setClickable(false);
            rdbAnswerB.setClickable(false);
            rdbAnswerC.setClickable(false);
            rdbAnswerD.setClickable(false);
            //    getcheckanswer(getItem(mPageNumber).getResult().toString());
        }
        rdgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(position).ChoiceId = checkedId;
                getItem(position).setTraloi(getChoiceFromID(checkedId));
            }
        });
    }
    public String getChoiceFromID(int ID) {
        if (ID == R.id.RdbA) {
            return "A";
        }else if (ID == R.id.RdbB){
            return "B";
        }else if (ID == R.id.RdbC){
            return "C";
        }else if(ID == R.id.RdbD){
            return"D";
        }
        else return "";
    }
    public void checkanswer() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_kiemtra);
        dialog.setTitle("Danh sach cac cau tra loi");


        activity_checkanser answer_adapter = new activity_checkanser(Questions, this);
        GridView checkanswer = (GridView) dialog.findViewById(R.id.checkanswer);
        checkanswer.setAdapter(answer_adapter);


        checkanswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dialog.dismiss();
            }
        });

        Button btnfinish, btnclose;
        btnclose = (Button) dialog.findViewById(R.id.btnclose);
        btnfinish = (Button) dialog.findViewById(R.id.btnFinish);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /////
                //timer.cancel();
                //result();
                dialog.dismiss();
                Intent intent = new Intent(activity_test5.this, activity_result.class);
                Bundle bundle = new Bundle();
                bundle.putInt("KQ", kq);
                bundle.putInt("Socau", position);
                intent.putExtra("MyPackage", bundle);

                startActivity(intent);
                mediaPlayer.stop();
                if (kq > HighScore) {
                    HighScore = kq;
                    SaveHighScore();
                    finish();
                }

            }
        });

        dialog.show();


    }
    public Question getItem(int position){
        return Questions.get(position);
    }
    //void ReadData() {
    //try {
//Tạo đối tượng DocumentBuilder (builder )
    //DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
    //DocumentBuilder builder = DBF.newDocumentBuilder();
//Tạo FileInputStream từ tập tin XML nguồn
    //InputStream in = getAssets().open("Data.xml");
//Dùng phương thức parse của đối tượng builder để tạo Document
    // Document doc = builder.parse(in);
    //Element root = doc.getDocumentElement();//lấy tag Root
    //NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
    // for (int i = 0; i < list.getLength(); i++) {
    //  Node node = list.item(i);

    //if (node instanceof Element) {
    //  Element Item = (Element) node;// lấy được tag Item

    // NodeList listChild = Item.getElementsByTagName("ID");
    // String ID = listChild.item(0).getTextContent();//lấy nội dung của tag ID
    //listChild = Item.getElementsByTagName("Question");// lấy tag Question
    // String Question = listChild.item(0).getTextContent();//lấy nội dung Question
    // listChild = Item.getElementsByTagName("AnswerA");
    // String AnswerA = listChild.item(0).getTextContent();
    // listChild = Item.getElementsByTagName("AnswerB");
    //  String AnswerB = listChild.item(0).getTextContent();
    //  listChild = Item.getElementsByTagName("AnswerC");
    //String AnswerC = listChild.item(0).getTextContent();
    // listChild = Item.getElementsByTagName("AnswerD");
    // String AnswerD = listChild.item(0).getTextContent();
    // listChild = Item.getElementsByTagName("Answer");
    // String Answer = listChild.item(0).getTextContent();


    //  QuestionNare Q1 = new QuestionNare();
    //  Q1.ID = ID;
    // Q1.Q = Question;
    //  Q1.AnswerA = AnswerA;
    //  Q1.AnswerB = AnswerB;
    //  Q1.AnswerC = AnswerC;
    //  Q1.AnswerD = AnswerD;
    //  Q1.Answer = Answer;
    //  L.add(Q1);

    // };
    // }
    //} catch (ParserConfigurationException e) {
    //e.printStackTrace();
    //} catch (FileNotFoundException e) {
    // e.printStackTrace();
    //} catch (SAXException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    //  e.printStackTrace();
    //}
    // }


    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInfuture, long countDownInterval) {
            super(millisInfuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinshed) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinshed));
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinshed);
            timer.setText(countTime);
        }



        // het thoi gian
        @Override
        public void onFinish() {

            // create intent de chuyen man hinh
            Intent intent = new Intent(activity_test5.this, activity_result.class);

            // tao bundle de chua tham so
            Bundle bundle = new Bundle();

            // truyen tham so vao bundle
            bundle.putInt("KQ", kq);
            bundle.putInt("Socau", Questions.size());
            intent.putExtra("MyPackage", bundle);

            // chuyen man hinh
            startActivity(intent);

            // finish man hinh hien tai
            finish();
        }
    }


    void LoadHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        if (sharedPreferences !=null){
            HighScore = sharedPreferences.getInt("H",0);
        }
    }
    void SaveHighScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H",HighScore);
        editor.apply();
    }
    public void findViewById() {
        txtQuestion = findViewById(R.id.TxtCauhoi);
        txtNumAnswer = findViewById(R.id.TxtKetqua);
        btnAnswer = findViewById(R.id.BtnTraloi);
        rdgAnswer = findViewById(R.id.RadioGroupTraloi);
        rdbAnswerA = findViewById(R.id.RdbA);
        rdbAnswerB = findViewById(R.id.RdbB);
        rdbAnswerC = findViewById(R.id.RdbC);
        rdbAnswerD = findViewById(R.id.RdbD);
        imgIcon = findViewById(R.id.ivIcon);
    }
    public void ReadDataFromSQL() {
        DatabaseHelper5 databaseHelper = new DatabaseHelper5(this);
        List<Question> Q1 = databaseHelper.getQuestion(muc);
        for (int i = 0; i < Q1.size(); i++) {
            Question question = new Question();
            question.ID = Q1.get(i).ID;
            question.Question = Q1.get(i).Question;
            question.AnswerA = Q1.get(i).AnswerA;
            question.AnswerB = Q1.get(i).AnswerB;
            question.AnswerC = Q1.get(i).AnswerC;
            question.AnswerD = Q1.get(i ).AnswerD;
            question.Answer = Q1.get(i).Answer;
            question.image = Q1.get(i).getImage();
            Questions.add(question);
        }

    }
}

