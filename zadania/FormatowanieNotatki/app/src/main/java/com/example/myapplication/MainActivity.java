package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button cursiveToggle;
    Button boldToggle;
    Button underlineToggle;
    TextView dateText;
    TextView textLength;

    private int seconds;
    private Boolean isBold = false;
    private Boolean isCursive = false;
    private Boolean isUnderline = false;

    public MainActivity() {
        super();
        this.isBold = false;
        this.isCursive = false;
        this.isUnderline = false;
    }

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            setDate(String.format("%ss", seconds));
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(runnable, 1000);

        editText = findViewById(R.id.EditText);
        textView = findViewById(R.id.textView);
        cursiveToggle = findViewById(R.id.buttonCursive);
        boldToggle = findViewById(R.id.buttonBold);
        underlineToggle = findViewById(R.id.buttonUnderline);
        dateText = findViewById(R.id.dateText);
        textLength = findViewById(R.id.textLength);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                changeText();
            }
        });

        cursiveToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCursive(!getCursive());
                changeText();
            }
        });

        boldToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBold(!getBold());
                changeText();
            }
        });

        underlineToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUnderline(!getUnderline());
                changeText();
            }
        });
    }

    protected Boolean getBold() {
        return isBold;
    }

    protected Boolean getCursive() {
        return isCursive;
    }

    protected Boolean getUnderline() {
        return isUnderline;
    }

    private void setUnderline(Boolean underline) {
        isUnderline = underline;
    }

    private void setBold(Boolean bold) {
        isBold = bold;
    }

    private void setCursive(Boolean cursive) {
        isCursive = cursive;
    }

    public void changeText() {
        SpannableString EditedText = new SpannableString(editText.getText());

        ArrayList<CharacterStyle> styles = new ArrayList<>();
        if (getCursive()) {
            styles.add(new StyleSpan(Typeface.ITALIC));
        }
        if (getBold()) {
            styles.add(new StyleSpan(Typeface.BOLD));
        }
        if (getUnderline()) {
            styles.add(new UnderlineSpan());
        }

        for (CharacterStyle style: styles) {
            EditedText.setSpan(style, 0, EditedText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        setTextLength(String.valueOf(EditedText.length()));
        setText(EditedText);
    }

    public void setText(SpannableString EditedText) {
        textView.setText(EditedText);
    }

    public void setDate (String text) {
        dateText.setText(text);
    }

    public void setTextLength (String text) {
        textLength.setText(text);
    }
}