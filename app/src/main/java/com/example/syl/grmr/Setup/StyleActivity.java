package com.example.syl.grmr.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.grmr.R;

public class StyleActivity extends AppCompatActivity {

    Button selectbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        selectbtn=(Button)findViewById(R.id.selectbutton1);
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent styleintent = new Intent(StyleActivity.this,Profile.class);
                styleintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(styleintent);
            }
        });
    }
}
