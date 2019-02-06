package com.alex.numbercar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.alex.numbercar.helper.MyKeyboard;
import com.alex.numbercar.helper.CustomKeyboard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private EditText editTextNumb, editTextReg;
    private InputConnection ic, ic1;
    private MyKeyboard keyboard;
    private Activity mHostActivity;
    private Context context;
    private String textNumber, textReg;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextNumb = (EditText) findViewById(R.id.edit_text_numb);
        editTextReg = (EditText) findViewById(R.id.edit_text_region);

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


        /*textNumber = editTextNumb.getText().toString();
        textReg = editTextReg.getText().toString();*/


        editTextNumb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*textNumber = editTextNumb.getText().toString();
                Toast.makeText(MainActivity.this, "1 " + textNumber, Toast.LENGTH_SHORT).show();*/

            }

            @Override
            public void afterTextChanged(Editable s) {
                textNumber = editTextNumb.getText().toString();
                Toast.makeText(MainActivity.this, "1 " + textNumber, Toast.LENGTH_SHORT).show();

            }
        });

        String completedString = textNumber + textReg;

       /* if (completedString.length()>0) {
            Toast.makeText(this, "12 " + completedString, Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "555", Toast.LENGTH_SHORT).show();*/


    }

    public void showCustomKeyboard() {
        keyboard.setVisibility(View.VISIBLE);
        keyboard.setEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }
    /*public boolean validate(String str){
        Pattern pattern;
        Matcher matcher;
        final String PATTERN = ("\\w\\d{3}\\w{2}\\d{2,3}");
        pattern = Pattern.compile(PATTERN);
        matcher = pattern.matcher(str);

        return matcher.matches();
    }*/



}

