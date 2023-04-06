package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    private Button correctBtn, incorrectBtn;
    private TextView opResult;
    private final String crr = "Correct";
    private final String incrr = "Incorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        correctBtn = findViewById(R.id.button_correct);
        incorrectBtn = findViewById(R.id.button_incorrect);
        opResult = findViewById(R.id.text_label);

        Bundle extras = getIntent().getExtras();

        // Get the terms as a string
        String operation = extras.getString("operation");
        opResult.setText(operation);

        correctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent correct = new Intent();
                correct.putExtra("correct",crr);
                setResult(Activity.RESULT_OK, correct);
                finish();
            }
        });

        incorrectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent incorrect = new Intent();
                incorrect.putExtra("incorrect",incrr);
                setResult(Activity.RESULT_CANCELED, incorrect);
                finish();
            }
        });
    }
}
