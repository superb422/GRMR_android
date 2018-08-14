package com.example.syl.grmr.NavigationMenu;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syl.grmr.R;

public class accountsetting extends AppCompatActivity {
    LinearLayout withdraw_btn;
    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);

        builder = new AlertDialog.Builder(this);

        withdraw_btn = (LinearLayout) findViewById(R.id.withdraw);
        withdraw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView title = new TextView(getApplicationContext());
                // 다이얼로그 타이틀 커스터마이징
                title.setText("갈래말래 계정을 탈퇴하시겠습니까?");
                title.setPadding(10, 100, 15, 40);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.BLACK);
                title.setTextSize(20);


                builder.setTitle("갈래말래 계정을 탈퇴하시겠습니까?")
                        .setNegativeButton("탈퇴하기",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .setPositiveButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                alert = builder.create();
                alert.setCustomTitle(title);


                /* 다이얼로그 창 커스터마이징 */
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                alert.getWindow().setAttributes(params);
                alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 다이얼로그시 뒷화면 밝게하기
                alert.getWindow().setBackgroundDrawableResource(R.drawable.dialogshape);

                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = 500;
                alert.show();

                Window window = alert.getWindow();
                window.setAttributes(params);

                /* 다이얼로그 버튼 커스터마이징 */
                Button btnPositive = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
                layoutParams.weight = 10;
                btnPositive.setLayoutParams(layoutParams);
                btnNegative.setLayoutParams(layoutParams);
            }
        });
    }
}
