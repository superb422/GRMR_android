package com.example.syl.grmr.addTravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;

import static com.example.syl.grmr.Adapter.FriendListviewAdapter.clickedFriend;
import static com.example.syl.grmr.Adapter.FriendListviewAdapter.friendPosition;
import static com.example.syl.grmr.Adapter.FriendListviewAdapter.list;
import static com.example.syl.grmr.addTravel.SearchFriendActivity.friendExit;


public class addTravel3Activity extends AppCompatActivity {

    static public Button friend_searchBar;

    private static ImageView []friendImage = new ImageView[4];
    private static ImageView []friendDelete = new ImageView[4];
    private static int []choosenImage = {0,0,0,0};

    ImageView empty;


    private static boolean []addedFriend={false,false,false,false};
    private static int cnt = 0;
    //뒤로가기 버튼
    @Override
    public void onBackPressed() {
        clickedFriend = false; //이전 버튼 누르고 다시 들어오면 검색했던 도시의 초기화를 위함.
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel3);


        //친구 검색 버튼
        friend_searchBar = (Button) findViewById(R.id.friend_search_bar);
        ImageView meImage = (ImageView) findViewById(R.id.me);

        meImage.setImageResource(R.drawable.ic_man); //본인 프로필 사진 추후 디비에 있는 사진 url 로 변경
        empty = (ImageView)findViewById(R.id.empty);
        ViewGroup.LayoutParams params = empty.getLayoutParams();
        params.height = 60;
        empty.setLayoutParams(params);

        /*
        추후  Click event 넣을 거임<추가된 친구 목록이 터치 시, 추가된 목록에서 삭제하기 위함>
         */


        for(int i =0 ; i< 4; i++) {
            friendImage[i] = (ImageView) findViewById(R.id.friend + i);
            friendDelete[i] = (ImageView) findViewById(R.id.delete + i);
            //friendText[i] = (TextView) findViewById(R.id.friendText + i);
        }



        if (clickedFriend == true && friendExit == false) {
            String choosenFriend = list.get(friendPosition);

            //추후 ChoosenFriend 를 매개변수로 db로 쏜 후 그 사람의 프로필 사진 경로 가져오기
            addedFriend[cnt] = true;


            for(int i = 0 ; i<cnt; i++){
                friendImage[i].setImageResource(choosenImage[i]);
                friendDelete[i].setImageResource(R.drawable.ic_delete_all);
                friendDelete[i].setLayoutParams(params);
            }

            for(int i =cnt ; i< 4; i++) {
                if(addedFriend[i]==true) {
                    friendImage[i].setImageResource(R.drawable.ic_man);
                    choosenImage[i] = R.drawable.ic_man; //반복해서 추가할 때, 이전에 추가했던 친구들 이미지 정보를 배열에 저장
                    friendDelete[i].setImageResource(R.drawable.ic_delete_all);
                    friendDelete[i].setLayoutParams(params);
                    System.out.println("ChoosenFriend================="+choosenFriend);
                }
            }
            cnt++;
        }
        else
        {

            for(int i = 0 ; i<cnt; i++){
                friendImage[i].setImageResource(choosenImage[i]);
                friendDelete[i].setImageResource(R.drawable.ic_delete_all);
                friendDelete[i].setLayoutParams(params);
            }
            friendExit = false;
        }


        //이전 버튼 누르고 다시 들어오면 등록했던 친구 리스트 초기화를 위함


            friend_searchBar.setText("동행자를 검색해 주세요.");




        friend_searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravel4Intent = new Intent(addTravel3Activity.this, SearchFriendActivity.class);
                addTravel4Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravel3Activity.this.startActivity(addTravel4Intent);

            }

        });


        /********************************Bring Text Info*******************************/
        //Bring the City Name
        TextView cityText = (TextView) findViewById(R.id.cityText);
        cityText.setText(addTravelActivity.cityText.getText());
        //Bring the departure date
        final TextView departureText = (TextView) findViewById(R.id.departureText);
        departureText.setText(addTravel2Activity.departureDate.getText());
        final TextView  homecomingText = (TextView) findViewById(R.id.homeComingText);
        homecomingText.setText(addTravel2_1Activity.homeComingDate.getText());


        /*********************Click nextButton***********************/
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravel4Intent = new Intent(addTravel3Activity.this, addTravel4Activity.class);
                addTravel4Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravel3Activity.this.startActivity(addTravel4Intent);

            }

        });

        /*********************Click previousButton***********************/
        Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravel2_1Intent = new Intent(addTravel3Activity.this, addTravel2_1Activity.class);
                addTravel2_1Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravel3Activity.this.startActivity(addTravel2_1Intent);
            }
        });


        /*********************Click closeButton***********************/
        ImageButton closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedFriend = false;
                Intent mainIntent = new Intent(addTravel3Activity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                addTravel3Activity.this.startActivity(mainIntent);
            }
        });



    }
}
