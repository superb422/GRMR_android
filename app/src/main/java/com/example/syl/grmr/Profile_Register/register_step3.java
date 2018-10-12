package com.example.syl.grmr.Profile_Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.syl.grmr.R;

import java.util.Arrays;

public class register_step3 extends AppCompatActivity {

    private static final String[] age = new String[]{"20","21","22","23","24","25","26","27","28"};
    Button btn_Step3;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);

        WheelView wva = (WheelView) findViewById(R.id.main_wv);

        wva.setOffset(1);
        wva.setItems(Arrays.asList(age));

        btn_Step3 = (Button)findViewById(R.id.step3_btn);
        btn_Step3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step3intent = new Intent(register_step3.this,register_step4.class);
                //step3intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step3intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
            }
        });

        back=(ImageView)findViewById(R.id.step3_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step3back = new Intent(register_step3.this,register_step2.class);
                step3back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step3back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent step3back = new Intent(register_step3.this,register_step2.class);
        step3back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step3back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
