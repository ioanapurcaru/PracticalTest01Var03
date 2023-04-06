package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("main", intent.getStringExtra("message"));
            if(intent.getAction().equals("ro.pub.cs.systems.eim.suma")) {
                String str = intent.getStringExtra("message");
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        }
    }
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

                Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent1.putExtra("nr1", firstTerm);
                intent1.putExtra("nr2", secondTerm);
                getApplicationContext().startService(intent1);

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


                Toast.makeText(getApplicationContext(), "Service started", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent1.putExtra("nr1", firstTerm);
                intent1.putExtra("nr2", secondTerm);
                getApplicationContext().startService(intent1);

            }
        });

        btnInvoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03SecondaryActivity.class);
                intent.putExtra("operation", sb.toString());
                startActivityForResult(intent, 1);
            }
        });


        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey("editText1")) {
                text1.setText(savedInstanceState.getString("editText1"));
            } else {
                text1.setText("");
            }
            if(savedInstanceState.containsKey("editText2")) {
                text2.setText(savedInstanceState.getString("editText2"));
            } else {
                text2.setText("");
            }
            Toast.makeText(PracticalTest01Var03MainActivity.this, "Text1: " +text1.getText().toString() + " Text2: " + text2.getText().toString(), Toast.LENGTH_SHORT).show();
        } else {
            text1.setText("");
            text2.setText("");
        }

        intentFilter.addAction("ro.pub.cs.systems.eim.suma");
        intentFilter.addAction("ro.pub.cs.systems.eim.dif");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editText1", text1.getText().toString());
        outState.putString("editText2", text2.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text1.setText(savedInstanceState.getString("editText1"));
        text2.setText(savedInstanceState.getString("editText2"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String result10 = data.getStringExtra("correct");
            Toast.makeText(this, "The result of the operation is " + result10, Toast.LENGTH_SHORT).show();
        } else {
            String result11 = data.getStringExtra("incorrect");
            Toast.makeText(this, "The result of the operation is " + result11, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
        getApplicationContext().stopService(intent2);
        Toast.makeText(getApplicationContext(), "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        Log.d("main", "onResume() method was invoked");
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d("main", "onPause() method was invoked");
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}