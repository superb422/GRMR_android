package com.example.syl.grmr.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.grmr.R;

public class CharActivity extends AppCompatActivity {
    Button select_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        select_btn=(Button)findViewById(R.id.selectbutton2);
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent charintent = new Intent(CharActivity.this,Profile.class);
                charintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(charintent);
            }
        });
    }
}
