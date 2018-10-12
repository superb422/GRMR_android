package com.example.syl.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syl.grmr.R;

public class register_step2 extends AppCompatActivity {

    ImageView btnman,btngirl,back;
    TextView textman,textgirl;
    Button step2;
    LinearLayout manLayout,girlLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step2);

        btnman = (ImageView)findViewById(R.id.btn_man); btngirl = (ImageView)findViewById(R.id.btn_girl);
        textman = (TextView)findViewById(R.id.man_text); textgirl = (TextView)findViewById(R.id.girl_text);
        step2=(Button)findViewById(R.id.step2_btn); back=(ImageView)findViewById(R.id.step2_back);
        manLayout=(LinearLayout)findViewById(R.id.man_layout); girlLayout=(LinearLayout)findViewById(R.id.girl_layout);

        manLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnman.setImageResource(R.drawable.ic_man_clicked);
                textman.setTextColor(Color.rgb(81,122,228));
                btngirl.setImageResource(R.drawable.ic_girl);
                textgirl.setTextColor(Color.rgb(219,219,219));
                step2.setBackgroundColor(Color.rgb(81,122,228));
                step2.setTextColor(Color.rgb(255,255,255));
            }
        });

        girlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnman.setImageResource(R.drawable.ic_man);
                textman.setTextColor(Color.rgb(219,219,219));
                btngirl.setImageResource(R.drawable.ic_girl_clicked);
                textgirl.setTextColor(Color.rgb(81,122,228));
                step2.setBackgroundColor(Color.rgb(81,122,228));
                step2.setTextColor(Color.rgb(255,255,255));
            }
        });

        step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step2intent = new Intent(register_step2.this,register_step3.class);
                //step2intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 쌓여있는 액티비티들 전부 삭제
                startActivity(step2intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step2back = new Intent(register_step2.this,register_step1.class);
                step2back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step2back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent step2back = new Intent(register_step2.this,register_step1.class);
        step2back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step2back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
