package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongh.grmr.R;

public class register_step1 extends AppCompatActivity {

    ImageView step1;
    EditText nickname;
    TextView checktext;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);

        step1 = (ImageView)findViewById(R.id.step1_btn);
        step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step1intent = new Intent(register_step1.this,register_step2.class);
                step1intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step1intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 오른쪽 화면이 들어오면서 왼쪽화면 아웃
                finish();
            }
        });

        exit = (ImageView)findViewById(R.id.step_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        checktext=(TextView)findViewById(R.id.step1_text_check);
        nickname=(EditText)findViewById(R.id.step1_nickname);

        nickname.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, final int i, int i1, int i2) { // 입력하기 전에

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력되는 텍스트에 변화가 있을 때
                checktext.setText(null);
            }

            @Override
            public void afterTextChanged(Editable editable) { // 입력이 끝났을 때
                if(nickname.getText().toString().equals("썸웨어")){
                    checktext.setText("이미 사용중인 닉네임이에요.");
                    checktext.setTextColor(Color.parseColor("#D0021B"));
                } else {
                    checktext.setText("짝짝짝! 사용가능한 닉네임이에요.");
                    checktext.setTextColor(Color.parseColor("#517AE4"));
                }
            }
        });
    }

}
