package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongh.grmr.Profile_Register.Util.job_dialog;
import com.example.dongh.grmr.R;


public class register_step2 extends AppCompatActivity implements View.OnClickListener{

    ImageView sexbtn,back;
    Button nxtbtn;
    TextView jobtxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step2);

        sexbtn = (ImageView)findViewById(R.id.sexbtn);
        nxtbtn=(Button) findViewById(R.id.step2_btn); back=(ImageView)findViewById(R.id.step2_back);
        jobtxt=(TextView)findViewById(R.id.job_txt);

        nxtbtn.setOnClickListener(this);
        back.setOnClickListener(this);
        jobtxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.step2_btn:
                Intent step2intent = new Intent(register_step2.this,register_step3.class);
                step2intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step2intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 오른쪽 화면이 들어오면서 왼쪽화면 아웃
                finish();
                break;
            case R.id.step2_back:
                Intent step2back = new Intent(register_step2.this, register_step1.class);
                step2back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step2back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
                break;
            case R.id.job_txt:
                job_dialog dialog = new job_dialog(this);
                dialog.JobDialogListener(new job_dialog.JobDialogListener() {
                    @Override
                    public void onPositiveClicked(String job) {
                        jobtxt.setText(job);
                        jobtxt.setTextColor(Color.rgb(81,122,228));
                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialog.show();
                break;
        }
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
