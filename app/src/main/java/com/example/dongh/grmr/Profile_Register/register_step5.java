package com.example.dongh.grmr.Profile_Register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dongh.grmr.R;

public class register_step5 extends AppCompatActivity {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15,back;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15;
    RelativeLayout nature1,nature2,nature3,nature4,nature5,nature6,nature7,nature8,nature9,nature10,nature11,nature12,nature13,nature14,nature15;
    Button step5;
    boolean btn_cnt=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step5);

        img1 = (ImageView) findViewById(R.id.n_img_1); txt1=(TextView)findViewById(R.id.n_text_1);  nature1=(RelativeLayout)findViewById(R.id.nature1);
        img2 = (ImageView) findViewById(R.id.n_img_2); txt2=(TextView)findViewById(R.id.n_text_2);  nature2=(RelativeLayout)findViewById(R.id.nature2);
        img3 = (ImageView) findViewById(R.id.n_img_3); txt3=(TextView)findViewById(R.id.n_text_3);  nature3=(RelativeLayout)findViewById(R.id.nature3);
        img4 = (ImageView) findViewById(R.id.n_img_4); txt4=(TextView)findViewById(R.id.n_text_4);  nature4=(RelativeLayout)findViewById(R.id.nature4);
        img5 = (ImageView) findViewById(R.id.n_img_5); txt5=(TextView)findViewById(R.id.n_text_5);  nature5=(RelativeLayout)findViewById(R.id.nature5);
        img6 = (ImageView) findViewById(R.id.n_img_6); txt6=(TextView)findViewById(R.id.n_text_6);  nature6=(RelativeLayout)findViewById(R.id.nature6);
        img7 = (ImageView) findViewById(R.id.n_img_7); txt7=(TextView)findViewById(R.id.n_text_7);  nature7=(RelativeLayout)findViewById(R.id.nature7);
        img8 = (ImageView) findViewById(R.id.n_img_8); txt8=(TextView)findViewById(R.id.n_text_8);  nature8=(RelativeLayout)findViewById(R.id.nature8);
        img9 = (ImageView) findViewById(R.id.n_img_9); txt9=(TextView)findViewById(R.id.n_text_9);  nature9=(RelativeLayout)findViewById(R.id.nature9);
        img10 = (ImageView) findViewById(R.id.n_img_10); txt10=(TextView)findViewById(R.id.n_text_10);  nature10=(RelativeLayout)findViewById(R.id.nature10);
        img11 = (ImageView) findViewById(R.id.n_img_11); txt11=(TextView)findViewById(R.id.n_text_11);  nature11=(RelativeLayout)findViewById(R.id.nature11);
        img12 = (ImageView) findViewById(R.id.n_img_12); txt12=(TextView)findViewById(R.id.n_text_12);  nature12=(RelativeLayout)findViewById(R.id.nature12);
        img13 = (ImageView) findViewById(R.id.n_img_13); txt13=(TextView)findViewById(R.id.n_text_13);  nature13=(RelativeLayout)findViewById(R.id.nature13);
        img14 = (ImageView) findViewById(R.id.n_img_14); txt14=(TextView)findViewById(R.id.n_text_14);  nature14=(RelativeLayout)findViewById(R.id.nature14);
        img15 = (ImageView) findViewById(R.id.n_img_15); txt15=(TextView)findViewById(R.id.n_text_15);  nature15=(RelativeLayout)findViewById(R.id.nature15);

        nature1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img1.setImageResource(R.drawable.ic_rectangle); txt1.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img1.setImageResource(0); txt1.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });

        nature2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img2.setImageResource(R.drawable.ic_rectangle); txt2.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img2.setImageResource(0); txt2.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });

        nature3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img3.setImageResource(R.drawable.ic_rectangle); txt3.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img3.setImageResource(0); txt3.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img4.setImageResource(R.drawable.ic_rectangle); txt4.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img4.setImageResource(0); txt4.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img5.setImageResource(R.drawable.ic_rectangle); txt5.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img5.setImageResource(0); txt5.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img6.setImageResource(R.drawable.ic_rectangle); txt6.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img6.setImageResource(0); txt6.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img7.setImageResource(R.drawable.ic_rectangle); txt7.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img7.setImageResource(0); txt7.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img8.setImageResource(R.drawable.ic_rectangle); txt8.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img8.setImageResource(0); txt8.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img9.setImageResource(R.drawable.ic_rectangle); txt9.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img9.setImageResource(0); txt9.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img10.setImageResource(R.drawable.ic_rectangle); txt10.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img10.setImageResource(0); txt10.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img11.setImageResource(R.drawable.ic_rectangle); txt11.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img11.setImageResource(0); txt11.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img12.setImageResource(R.drawable.ic_rectangle); txt12.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img12.setImageResource(0); txt12.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img13.setImageResource(R.drawable.ic_rectangle); txt13.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img13.setImageResource(0); txt13.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img14.setImageResource(R.drawable.ic_rectangle); txt14.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img14.setImageResource(0); txt14.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });
        nature15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_cnt) {
                    img15.setImageResource(R.drawable.ic_rectangle); txt15.setTextColor(Color.rgb(81, 122, 228)); btn_cnt=false;
                } else {
                    img15.setImageResource(0); txt15.setTextColor(Color.rgb(196, 196, 196)); btn_cnt=true;
                }
            }
        });

        step5=(Button)findViewById(R.id.step5_btn);
        step5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step5intent = new Intent(register_step5.this,register_step6.class);
                //step5intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step5intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
            }
        });

        back = (ImageView)findViewById(R.id.step5_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent step5back = new Intent(register_step5.this,register_step4.class);
                step5back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(step5back);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
            }
        });






    }
    @Override
    public void onBackPressed() {
        Intent step5back = new Intent(register_step5.this,register_step4.class);
        step5back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(step5back);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
        super.onBackPressed();
    }
}
