package com.example.syl.grmr.NavigationMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Adapter.SwipeController;
import com.example.syl.grmr.Adapter.SwipeControllerActions;
import com.example.syl.grmr.Adapter.UserDataAdapter;
import com.example.syl.grmr.Constructor.User;
import com.example.syl.grmr.R;

import java.util.ArrayList;
import java.util.List;

public class FriendMenu extends AppCompatActivity {

    private UserDataAdapter mAdapter;
    SwipeController swipeController = null;
    ImageView friendplus;
    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_menu);

        friendplus = (ImageView) findViewById(R.id.plus_btn);
        friendplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent friendIntent = new Intent(FriendMenu.this, addFriend.class);
                startActivity(friendIntent);
            }
        });


        setUserDataAdapter();
        setupRecyclerView();
    }


    private void setUserDataAdapter() {
        List<User> Users = new ArrayList<>();

        User user = new User();
        user.setName("준용");
        user.setImage(R.drawable.kakao_default_profile_image);
        Users.add(user);


        mAdapter = new UserDataAdapter(Users);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        final TextView title = new TextView(getApplicationContext());
        // 다이얼로그 타이틀 커스터마이징
        title.setText("친구 삭제를 하시겠습니까?");
        title.setPadding(10, 100, 15, 40);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);

        builder = new AlertDialog.Builder(this);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(final int position) {

                builder.setTitle("친구 삭제를 하시겠습니까?")
                        .setNegativeButton("삭제",
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        /* 친구 목록에서 삭제 */
                                        mAdapter.Users.remove(position);
                                        mAdapter.notifyItemRemoved(position);
                                        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
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

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

}
