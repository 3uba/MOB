package com.example.busy3;

import androidx.appcompat.app.AppCompatActivity;

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

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis() / 100L;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (;;) {
                            long currentTime = System.currentTimeMillis() / 100L;
                            if(currentTime == startTime + 100) {
                                break;
                            }
                            double percentage = (double)  (currentTime - startTime) ;
                            final String percentageString = String.format("%.0f%%", percentage);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(percentageString);
                                }
                            });
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonClickedChangeText();
                            }
                        });

                    }
                }).start();
            }
        });
    }

    private void buttonClickedChangeText() {
        textView.setText("Zakończona czasochłonna funkcja");
    }
}
