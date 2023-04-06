package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    private Button plusBtton, minusButton, btnInvoke;
    private EditText text1, text2;
    private TextView textView;
    private int firstTerm, secondTerm;
    private StringBuilder sb;
    private int result;
    IntentFilter intentFilter = new IntentFilter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        plusBtton = findViewById(R.id.button_add);
        minusButton = findViewById(R.id.button_minus);
        text1 = findViewById(R.id.editText_firstTerm);
        text2 = findViewById(R.id.editText_secondTerm);
        textView = findViewById(R.id.terms);
        btnInvoke = findViewById(R.id.btn_invoke_activity);
        sb = new StringBuilder();
        text1.setSaveEnabled(false);
        text2.setSaveEnabled(false);

        plusBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                String term1 = text1.getText().toString();
                String term2 = text2.getText().toString();
                try {
                    firstTerm = Integer.parseInt(term1);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "The string is not a valid integer", Toast.LENGTH_SHORT).show();
                }
                try {
                    secondTerm = Integer.parseInt(term2);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "The string is not a valid integer", Toast.LENGTH_SHORT).show();
                }
                result = firstTerm + secondTerm;
                String resultString = String.valueOf(result);

                sb.append(term1);
                sb.append(" + ");
                sb.append(term2);
                sb.append(" = ");
                sb.append(resultString);
                textView.setText(sb.toString());

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                String term1 = text1.getText().toString();
                String term2 = text2.getText().toString();
                try {
                    firstTerm = Integer.parseInt(term1);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "The string is not a valid integer", Toast.LENGTH_SHORT).show();
                }
                try {
                    secondTerm = Integer.parseInt(term2);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "The string is not a valid integer", Toast.LENGTH_SHORT).show();
                }
                result = firstTerm - secondTerm;
                String resultString = String.valueOf(result);

                sb.append(term1);
                sb.append(" - ");
                sb.append(term2
                );
                sb.append(" = ");
                sb.append(resultString);
                textView.setText(sb.toString());

            }
        });
    }
}