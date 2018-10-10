package com.example.syl.grmr.Login;

import android.app.Service;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.syl.grmr.R;

public class EmailregisterActivity extends AppCompatActivity {

    EditText emailtext,pwtext;
    ImageView mainicon,maincomma,maindot;
    RelativeLayout registerlayout;
    SoftKeyboard softKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        emailtext=(EditText)findViewById(R.id.email_id);
        pwtext=(EditText)findViewById(R.id.password_id);
        mainicon=(ImageView)findViewById(R.id.main_icon);
        maincomma=(ImageView)findViewById(R.id.main_icon_comma);
        maindot=(ImageView)findViewById(R.id.main_icon_dot);
        registerlayout=(RelativeLayout)findViewById(R.id.register_layout);

        InputMethodManager controlManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        softKeyboard = new SoftKeyboard(registerlayout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mainicon.setVisibility(View.VISIBLE);
                        maincomma.setVisibility(View.VISIBLE);
                        maindot.setVisibility(View.VISIBLE);

                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mainicon.setVisibility(View.GONE);
                        maincomma.setVisibility(View.GONE);
                        maindot.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        softKeyboard.unRegisterSoftKeyboardCallback();
    }
}
