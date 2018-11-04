package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongh.grmr.R;

import static java.lang.Thread.sleep;

public class register_step1 extends AppCompatActivity {

    Button step1;
    EditText nickname;
    TextView checktext;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);

        step1 = (Button)findViewById(R.id.step1_btn);
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

        checktext=(TextView)findViewById(R.id.check_text);
        nickname = (EditText)findViewById(R.id.step1_nickname);
        nickname.addTextChangedListener(new TextWatcher() {
            String testnick="김동휘";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { //입력되는 텍스트에 변화가 있을 때
                nickname.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_nick_delete,0);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력이 끝났을 때
                //nickname.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_nick_delete,0);
                if(nickname.getText().toString().equals(testnick)){
                    checktext.setText("짝짝짝! 사용가능한 닉네임이에요");
                    checktext.setTextColor(Color.rgb(81, 122, 228));
                    step1.setBackgroundColor(Color.rgb(81,122,228));
                    step1.setTextColor(Color.rgb(255,255,255));
                } else {
                    checktext.setText("이미 사용중인 닉네임이에요");
                    checktext.setTextColor(Color.rgb(208, 2, 27));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { // 입력하기 전에

            }
        });

        nickname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT=0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(motionEvent.getAction() == MotionEvent.ACTION_UP) { // drawableright 이미지 클릭시 이벤트
                    if(motionEvent.getRawX() >= (nickname.getRight() - nickname.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        nickname.setText(null);

                        return false;
                    }
                }

                return false;
            }
        });
    }
}
