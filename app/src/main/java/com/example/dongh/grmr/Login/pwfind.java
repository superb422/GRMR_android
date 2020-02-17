package com.example.dongh.grmr.Login;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dongh.grmr.R;


public class pwfind extends AppCompatActivity {

    Button checkbtn;
    TextView checktext,pwtext1,pwtext2;
    ExtensionEditText pwfind_email;
    int com=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwfind);

        checkbtn = (Button) findViewById(R.id.pwfind_check);
        pwtext1 = (TextView)findViewById(R.id.pw_text1); pwtext2 = (TextView)findViewById(R.id.pw_text2);
        pwfind_email = (ExtensionEditText) findViewById(R.id.pw_find_email);


        if(!checktext.getText().toString().equals("확인")) {
            checkbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }



        pwfind_email.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, final int i, int i1, int i2) {
                pwfind_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_nick_delete, 0);

                if(checktext.getText().toString().equals("전송")) {
                    checkbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pwtext1.setText("이메일이 전송되었습니다.");
                            pwtext2.setText("비밀번호 재설정 링크를 보냈습니다.\n이메일을 확인해 비밀번호를 재설정해주세요.");
                            checktext.setText("재전송");
                            checktext.setTextColor(Color.parseColor("#FFFFFF"));
                            com=1;
                        }
                    });
                }else {
                    if(com==1) {
                        checkbtn.setBackgroundResource(R.drawable.btn_skyblue);
                        checkbtn.setText("재전송");
                        checkbtn.setTextColor(Color.parseColor("#FFFFFF"));
                    }else{
                        checkbtn.setBackgroundResource(R.drawable.btn_skyblue);
                        checkbtn.setText("전송");
                        checkbtn.setTextColor(Color.parseColor("#FFFFFF"));
                    }

                }


                pwfind_email.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        final int DRAWABLE_LEFT = 0;
                        final int DRAWABLE_TOP = 1;
                        final int DRAWABLE_RIGHT = 2;
                        final int DRAWABLE_BOTTOM = 3;


                        if ((motionEvent.getAction() == MotionEvent.ACTION_UP)) { // drawableright 이미지 클릭시 이벤트
                            if (motionEvent.getRawX() >= (pwfind_email.getRight() - pwfind_email.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                // your action here
                                pwfind_email.setText(null);
                                checkbtn.setBackgroundResource(R.drawable.btn_gray);
                                checktext.setText("확인");
                                checktext.setTextColor(Color.parseColor("#979797"));

                                return false;
                            }
                        }

                        return false;
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    /* 이미지 비교 메소드*/
    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }



}
