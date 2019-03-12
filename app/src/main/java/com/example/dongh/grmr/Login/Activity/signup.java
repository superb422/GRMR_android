package com.example.dongh.grmr.Login.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dongh.grmr.Login.ExtensionEditText;
import com.example.dongh.grmr.R;

public class signup extends AppCompatActivity {
    ExtensionEditText signup_email,signup_pw,signup_pw2;
    TextView email_check,pw_check,pw_check2;
    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_email=(ExtensionEditText)findViewById(R.id.signup_email); email_check=(TextView)findViewById(R.id.signup_email_check);
        signup_pw=(ExtensionEditText)findViewById(R.id.signup_email_pw); pw_check=(TextView)findViewById(R.id.signup_pw_check);
        signup_pw2=(ExtensionEditText)findViewById(R.id.signup_email_pw2); pw_check2=(TextView)findViewById(R.id.signup_pw_check2);
        signup_btn=(Button) findViewById(R.id.signup_btn);

        signup_email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, final int i, int i1, int i2) { //입력되는 텍스트에 변화가 있을 때
                signup_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_nick_delete, 0);


                signup_email.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;


                        if ((motionEvent.getAction() == MotionEvent.ACTION_UP)) { // drawableright 이미지 클릭시 이벤트
                            if (motionEvent.getRawX() >= (signup_email.getRight() - signup_email.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here
                                signup_email.setText(null);

                                return false;
                            }
                        }

                        return false;
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력이 끝났을 때

            }

            @Override
            public void afterTextChanged(Editable editable) { // 입력하기 전에

            }
        });
        signup_pw.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, final int i, int i1, int i2) { //입력되는 텍스트에 변화가 있을 때
                signup_pw.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_nick_delete, 0);


                signup_pw.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;


                        if ((motionEvent.getAction() == MotionEvent.ACTION_UP)) { // drawableright 이미지 클릭시 이벤트
                            if (motionEvent.getRawX() >= (signup_pw.getRight() - signup_pw.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here
                                signup_pw.setText(null);

                                return false;
                            }
                        }

                        return false;
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력이 끝났을 때

            }

            @Override
            public void afterTextChanged(Editable editable) { // 입력하기 전에

            }
        });
        signup_pw2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, final int i, int i1, int i2) {
                signup_pw2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_nick_delete, 0);


                signup_pw2.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;


                        if ((motionEvent.getAction() == MotionEvent.ACTION_UP)) { // drawableright 이미지 클릭시 이벤트
                            if (motionEvent.getRawX() >= (signup_pw2.getRight() - signup_pw2.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here
                                signup_pw2.setText(null);

                                return false;
                            }
                        }

                        return false;
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signup_email.getText().toString().equals("sumwhere@naver.com")) {
                    email_check.setText("이미 존재하는 계정입니다.");
                    email_check.setTextColor(Color.parseColor("#D0021B"));
                } else {
                    email_check.setText("가능한 계정입니다.");
                    email_check.setTextColor(Color.parseColor("#517AE4"));
                }

                if (signup_pw.getText().toString().length() < 8) {
                    pw_check.setText("8자 이상 입력해주세요.");
                    pw_check.setTextColor(Color.parseColor("#D0021B"));
                } else {
                    pw_check.setText("훌륭해요.");
                    pw_check.setTextColor(Color.parseColor("#517AE4"));
                }

                if (!signup_pw2.getText().toString().equals(signup_pw.getText().toString())){
                    pw_check2.setText("비밀번호가 다릅니다");
                    pw_check2.setTextColor(Color.parseColor("#D0021B"));
                } else
                    pw_check2.setText(null);
            }
        });

    }

}
