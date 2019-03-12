package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dongh.grmr.R;

public class register_step4 extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    Button step4;
    Button [] natures = new Button[16];
    boolean [] btn_clicked = new boolean[16];
    String fileName1;
    int n_nature=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step4);

        for(int i=1;i< natures.length;i++)
        {
            fileName1 = "nature"+i;
            natures[i] = (Button)findViewById(getResources().getIdentifier(fileName1, "id", getPackageName()));
            natures[i].setOnClickListener(this);

            btn_clicked[i]=true;
        }

        step4=(Button) findViewById(R.id.step4_btn);
        step4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n_nature>4) {
                    Intent step4intent = new Intent(register_step4.this, register_step5.class);
                    step4intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(step4intent);
                    overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                    finish();
                }
            }
        });

        back = (ImageView)findViewById(R.id.step4_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step4back = new Intent(register_step4.this,register_step3.class);
                step4back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step4back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        for(int i=1;i<natures.length;i++){
            if(view.getId()==natures[i].getId()){
                if(btn_clicked[i]==true) {
                    natures[i].setBackgroundResource(R.drawable.ic_rectangle);
                    natures[i].setTextColor(Color.rgb(81, 122, 228));
                    btn_clicked[i]=false;
                    n_nature++;
                    if(n_nature>4) {
                        step4.setBackgroundResource(R.drawable.btn_skyblue);
                        step4.setTextColor(Color.rgb(255,255,255));
                    }
                }else{
                    natures[i].setBackgroundResource(0);
                    natures[i].setTextColor(Color.rgb(196, 196, 196));
                    n_nature--;
                    if(n_nature<5) {
                        step4.setBackgroundResource(R.drawable.btn_gray);
                        step4.setTextColor(Color.rgb(151, 151, 151));
                    }
                    btn_clicked[i]=true;
                }

            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent step4back = new Intent(register_step4.this,register_step3.class);
        step4back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step4back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
