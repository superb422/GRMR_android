package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dongh.grmr.Main.MainActivity;
import com.example.dongh.grmr.R;

import java.util.ArrayList;
import java.util.List;

public class register_step6 extends AppCompatActivity {
    LinearLayout linear1;
    RelativeLayout Relative1;
    TextView txtnature;
    ImageView natureimg;
    Button step6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step6);

        linear1=(LinearLayout)findViewById(R.id.style_nature); txtnature=(TextView)findViewById(R.id.txt_nature); natureimg=(ImageView)findViewById(R.id.nature_img);
        Relative1=(RelativeLayout)findViewById(R.id.style_nature_inf); Relative1.setVisibility(View.GONE);
        linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Relative1.setVisibility(View.VISIBLE);
                txtnature.setTextColor(Color.rgb(81, 122, 228));
                natureimg.setImageResource(R.drawable.ic_radioclickedbutton);
            }
        });

        step6 = (Button)findViewById(R.id.step6_btn);
        step6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stepfinish = new Intent(register_step6.this,MainActivity.class);
                stepfinish.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(stepfinish);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent step6back = new Intent(register_step6.this,register_step5.class);
        step6back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step6back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
