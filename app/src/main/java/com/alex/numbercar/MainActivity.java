package com.alex.numbercar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alex.numbercar.helper.MyKeyboard;
import com.alex.numbercar.helper.ReplaceString;


public class MainActivity extends AppCompatActivity {

    private EditText editTextNumb, editTextReg;
    private InputConnection ic, ic1;
    private MyKeyboard keyboard;
    private Context context;
    private String textNumber, textReg;
    private Button sendBtn;
    private String replacedNumber;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sendBtn = (Button)findViewById(R.id.button);
        editTextNumb = (EditText) findViewById(R.id.edit_text_numb);
        editTextReg = (EditText) findViewById(R.id.edit_text_region);

        editTextNumb.addTextChangedListener(completedText);
        editTextReg.addTextChangedListener(completedText);
        editTextReg.setFocusable(false);

        sendBtn.setEnabled(false);
        sendBtn.setBackgroundResource(R.drawable.roundrectdisable);
        sendBtn.setTextColor(0x6EBEBEBE);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        keyboard.setVisibility(View.GONE);


        editTextNumb.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextNumb.setRawInputType(InputType.TYPE_CLASS_TEXT);
                    editTextNumb.setTextIsSelectable(true);
                    ic = editTextNumb.onCreateInputConnection(new EditorInfo());
                    keyboard.setInputConnection(ic);
                }
            }
        });

        editTextNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomKeyboard();
            }
        });

        editTextReg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextReg.setRawInputType(InputType.TYPE_CLASS_TEXT);
                    editTextReg.setTextIsSelectable(true);
                    ic = editTextReg.onCreateInputConnection(new EditorInfo());
                    keyboard.setInputConnection(ic);
                }
            }
        });

        editTextReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomKeyboard();
            }
        });

        sendString();

    }

    private TextWatcher completedText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            textNumber = editTextNumb.getText().toString();
            textReg = editTextReg.getText().toString();

            if (s.length() > 5) {
                editTextReg.setFocusableInTouchMode(true);
                editTextReg.requestFocus();
            }

            if (textNumber.length() == 6 & textReg.length() > 1) {
                sendBtn.setEnabled(true);
                sendBtn.setBackgroundResource(R.drawable.roundrect);
                sendBtn.setTextColor(0xFFFFFFFF);
            } else {
                if (textNumber.length() != 6 | textReg.length() <= 1) {
                    sendBtn.setEnabled(false);
                    sendBtn.setBackgroundResource(R.drawable.roundrectdisable);
                    sendBtn.setTextColor(0x6EBEBEBE);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void sendString() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String completedString = textNumber + textReg;
                String pattern = "[А-Я]{1}[0-9]{3}[А-Я]{2}[0-9]{2,3}";

                if (completedString.matches(pattern)) {
                    ReplaceString rs = new ReplaceString();
                    replacedNumber = rs.replaceChar(textNumber);
                    String completedReplasedString = replacedNumber+textReg;
                    Toast.makeText(MainActivity.this, completedReplasedString, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Госномер некорректен", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showCustomKeyboard() {
        keyboard.setVisibility(View.VISIBLE);
        keyboard.setEnabled(true);
    }
}

