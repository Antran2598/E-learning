package son.com.doanandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Checkpoint extends  CorrectWord{
    TextView textScreen,textQuestion,textTitle,textBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkpoint);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textBtnBack = (TextView) findViewById(R.id.textBtnBack);

        textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        textBtnBack.setTypeface(typeface);

    }
}
