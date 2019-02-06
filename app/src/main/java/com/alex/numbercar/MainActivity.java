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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.alex.numbercar.helper.MyKeyboard;
import com.alex.numbercar.helper.CustomKeyboard;


public class MainActivity extends AppCompatActivity {

    private EditText editTextNumb, editTextReg;
    private InputConnection ic, ic1;
    private MyKeyboard keyboard;
    private Activity mHostActivity;

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

        editTextNumb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = editTextNumb.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    public void showCustomKeyboard() {
        keyboard.setVisibility(View.VISIBLE);
        keyboard.setEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

}
