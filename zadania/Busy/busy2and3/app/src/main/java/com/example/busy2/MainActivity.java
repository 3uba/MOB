package com.example.busy2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < 3; i++) {
                            for(int j = 0; j < Integer.MAX_VALUE; j++) {}
                        }

                        textView.post(new Runnable() {
                            @Override
                            public void run() {
//                                buttonClickedChangeText();
                                buttonClickedChangeStyle();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    // busy2
    private void buttonClickedChangeText() {
        textView.setText("Zakonczona czasochlonna funkcje");
    }

    // busy3
    private void buttonClickedChangeStyle() {
        textView.setTypeface(null, Typeface.BOLD);
    }
}