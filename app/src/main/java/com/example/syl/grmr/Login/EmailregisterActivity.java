package com.example.syl.grmr.Login;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.syl.grmr.R;

import static java.lang.Thread.sleep;

public class EmailregisterActivity extends AppCompatActivity {

    ExtensionEditText emailtext,pwtext;
    ImageView mainicon,emailback;
    RelativeLayout registerlayout;
    SoftKeyboard softKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        emailtext=(ExtensionEditText)findViewById(R.id.email_id);
        emailtext.requestFocus();
        emailtext.setHiddenKeyboardOnBackPressed(false); // 뒤로가기 버튼 비활성화
        pwtext=(ExtensionEditText)findViewById(R.id.password_id);
        pwtext.setHiddenKeyboardOnBackPressed(false);


        emailback = (ImageView)findViewById(R.id.email_back);
        emailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbtn = new Intent(EmailregisterActivity.this,LoginActivity.class);
                backbtn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backbtn);
                finish();
            }
        });
        mainicon=(ImageView)findViewById(R.id.main_icon);
        registerlayout=(RelativeLayout)findViewById(R.id.register_layout);

        InputMethodManager controlManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);

        /*softKeyboard = new SoftKeyboard(registerlayout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                       // mainicon.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //mainicon.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });*/
    }
}
