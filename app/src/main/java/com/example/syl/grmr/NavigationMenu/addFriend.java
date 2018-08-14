package com.example.syl.grmr.NavigationMenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syl.grmr.Adapter.FriendListviewAdapter;
import com.example.syl.grmr.R;
import com.example.syl.grmr.addTravel.addTravel3Activity;

import java.util.ArrayList;
import java.util.List;

public class addFriend extends AppCompatActivity {

    LinearLayout UserInfo;
    TextView UserEmpty;
    ImageButton search_button;
    AlertDialog.Builder builder;
    AlertDialog alert;
    Button friend_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        View view = this.getCurrentFocus(); // 키보드 숨기기

        UserInfo = (LinearLayout)findViewById(R.id.User_info);
        UserEmpty = (TextView)findViewById(R.id.User_empty);
        search_button = (ImageButton)findViewById(R.id.search_btn);

        UserInfo.setVisibility(View.GONE);
        UserEmpty.setVisibility(View.GONE);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* 키보드 숨기기 */
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                UserEmpty.setVisibility(View.VISIBLE);
            }
        });

        builder = new AlertDialog.Builder(this);
        friend_request = (Button)findViewById(R.id.friend_req);
        friend_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final TextView title = new TextView(getApplicationContext());
                // 다이얼로그 타이틀 커스터마이징
                title.setText("친구 요청이"+"\n" + "완료 되었습니다.");
                title.setPadding(10, 120, 15, 120);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.BLACK);
                title.setTextSize(20);
                alert = builder.create();
                alert.setCustomTitle(title);

                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                alert.getWindow().setAttributes(params);
                alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 다이얼로그시 뒷화면 밝게하기
                alert.getWindow().setBackgroundDrawableResource(R.drawable.dialogshape);
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                alert.show();
            }
        });
    }


}
