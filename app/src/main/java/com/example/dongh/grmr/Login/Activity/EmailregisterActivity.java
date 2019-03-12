package com.example.dongh.grmr.Login.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dongh.grmr.Login.ExtensionEditText;
import com.example.dongh.grmr.Login.SoftKeyboard;
import com.example.dongh.grmr.Profile_Register.register_step1;
import com.example.dongh.grmr.R;

public class EmailregisterActivity extends AppCompatActivity implements View.OnClickListener{

    ExtensionEditText emailtext,pwtext;
    ImageView mainicon,loginbtn;
    LinearLayout registerlayout,idpw_layout;
    RelativeLayout emailback;
    SoftKeyboard softKeyboard;
    TextView id_equal,pw_equal,pw_find;
    InputMethodManager controlManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        mainicon=(ImageView)findViewById(R.id.main_icon);
        id_equal=(TextView)findViewById(R.id.id_equal); pw_equal=(TextView)findViewById(R.id.pw_equal);
        emailtext=(ExtensionEditText)findViewById(R.id.email_id); pwtext=(ExtensionEditText)findViewById(R.id.password_id);
        emailback=(RelativeLayout) findViewById(R.id.email_back);
        idpw_layout=(LinearLayout)findViewById(R.id.idpw_layout);
        loginbtn = (ImageView) findViewById(R.id.register_button);
        pw_find = (TextView)findViewById(R.id.pw_find);
        //emailtext.requestFocus();

        //emailtext.setTextColor(Color.parseColor("#000000"));

        pw_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(EmailregisterActivity.this,pwfind.class);
                startActivity(it);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textid="superb422@naver.com";
                String textpw="1234";

                if(emailtext.getText().toString().equals(textid) && pwtext.getText().toString().equals(textpw)){
                    Intent it = new Intent(EmailregisterActivity.this,register_step1.class);
                    startActivity(it);
                } else {
                    int color = Color.parseColor("#d0021b");
                    emailtext.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    id_equal.setText("존재하지 않는 계정입니다.");

                    pwtext.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    pw_equal.setText("비밀번호가 틀렸습니다.");
                }

            }
        });

        /*emailtext.addTextChangedListener(new TextWatcher() { 실시간 아이디,비번일치 확인용 리스너
            String textid="superb422@naver.com";


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(emailtext.getText().toString().equals(textid)){
                    id_equal.setText("아이디 일치.");
                } else {
                    int color = Color.parseColor("#d0021b");
                    emailtext.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    id_equal.setText("존재하지 않는 계정입니다.");
                }
            }
        });
        pwtext.addTextChangedListener(new TextWatcher() {
            String textpw="1234";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        emailback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbtn = new Intent(EmailregisterActivity.this,LoginActivity.class);
                backbtn.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backbtn);
                finish();
            }
        });

        registerlayout=(LinearLayout)findViewById(R.id.register_layout);


        //emailtext.setKeyListener(null);
        /*변경하고 싶은 레이아웃의 파라미터 값을 가져 옴*/
        final LinearLayout.LayoutParams llControl = (LinearLayout.LayoutParams)idpw_layout.getLayoutParams();

        emailtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                llControl.setMargins(0,20,0,100);
                mainicon.setVisibility(View.INVISIBLE);
            }
        });
        //idpw_layout.setLayoutParams(llControl);
        //pwtext.setKeyListener(null);
        pwtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                    mainicon.setVisibility(View.VISIBLE);
                llControl.setMargins(0,20,0,100);
                mainicon.setVisibility(View.INVISIBLE);
            }

        });

        emailtext.setOnClickListener(this);
        pwtext.setOnClickListener(this);

        /*controlManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);

        softKeyboard = new SoftKeyboard(registerlayout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });*/
        idpw_layout.setLayoutParams(llControl);

        emailtext.setHiddenKeyboardOnBackPressed(false); // 키보드 활성화시 뒤로가기버튼 비활성화
        pwtext.setHiddenKeyboardOnBackPressed(false);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_id:
                //controlManager.showSoftInput(emailtext,0);
                break;
            case R.id.password_id:
                //controlManager.showSoftInput(pwtext,0);
                break;
        }
    }
}
