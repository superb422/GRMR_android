package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dongh.grmr.Profile_Register.Util.loc_style_dialog;
import com.example.dongh.grmr.R;

import java.util.List;

public class register_step5 extends AppCompatActivity implements View.OnClickListener {

    ImageView step5, question1, question2, question3;
    ImageView [] style_locs_img = new ImageView[6];
    TextView [] style_locs_txt = new TextView[6];
    String fileName1,fileName2;
    LinearLayout loc_layout;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step5);

        step5 = (ImageView) findViewById(R.id.step5_back);
        question1 = (ImageView) findViewById(R.id.step5_q1);
        question2 = (ImageView) findViewById(R.id.step5_q2);
        question3 = (ImageView) findViewById(R.id.step5_q3);


        step5.setOnClickListener(this);
        question1.setOnClickListener(this);
        question2.setOnClickListener(this);
        question3.setOnClickListener(this);
        loc_layout=(LinearLayout)findViewById(R.id.loc_layout);

        for(int i=1;i< style_locs_img.length;i++)
        {
            fileName1 = "loc_img"+i;
            fileName2 = "loc_txt"+i;

            style_locs_img[i-1] = (ImageView) findViewById(getResources().getIdentifier(fileName1, "id", getPackageName()));
            style_locs_txt[i-1] = (TextView) findViewById(getResources().getIdentifier(fileName2, "id", getPackageName()));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.step5_back:
                Intent step5back = new Intent(register_step5.this, register_step4.class);
                step5back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step5back);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
                break;

            case R.id.step5_q1:
                i=0;
                loc_style_dialog dialog = new loc_style_dialog(this);
                dialog.StyleDialogListener(new loc_style_dialog.StyleDialogListener() {
                    @Override
                    public void onSelectedStyle(List loc_list) {
                        loc_layout.setVisibility(View.VISIBLE);
                        for(Object object : loc_list) {
                            String element = (String) object;

                                switch(element){
                                    case "카페":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_cafe);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "펍":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_pub);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "바다":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_sea);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "산":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_mtn);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "공원":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_park);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "호수":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_lake);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "테마파크":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_temapark);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "전시회":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_exhibition);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;
                                        break;
                                    case "포토존":
                                        style_locs_img[i].setImageResource(R.drawable.ic_style_photozone);
                                        style_locs_txt[i].setText("<"+element+">");
                                        i++;

                                }
                        }

                    }
                });
                dialog.show();
                break;
        }
    }
    @Override
    public void onBackPressed () {
        Intent step5back = new Intent(register_step5.this, register_step4.class);
        step5back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step5back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
