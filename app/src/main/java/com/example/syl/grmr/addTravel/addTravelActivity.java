package com.example.syl.grmr.addTravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.MainFragments.TravelFragment;
import com.example.syl.grmr.R;

import static com.example.syl.grmr.Adapter.CityListviewAdapter.cityPosition;
import static com.example.syl.grmr.Adapter.CityListviewAdapter.clickedCity;
import static com.example.syl.grmr.Adapter.CityListviewAdapter.list;

public class addTravelActivity extends AppCompatActivity {

    static public Button city_searchBar;
    static public TextView cityText;
    static public TextView countryText;



    //뒤로가기 버튼
    @Override
    public void onBackPressed() {
        clickedCity = false; //이전 버튼 누르고 다시 들어오면 검색했던 도시의 초기화를 위함.
        Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel);

        //x버튼인 닫기를 누르면 fragment의 HomeFragment가 아닌 TravelFragment를 실행시키기 위함. 이후 MainActivity에서 사용할 것임.
        TravelFragment.startedAddTravel = true;


        //도시 검색 버튼
        city_searchBar = (Button) findViewById(R.id.city_search_bar);

        //도시, 나라 변수 In 카드
        countryText = (TextView) findViewById(R.id.countryText);
        cityText = (TextView) findViewById(R.id.cityText);



        if (clickedCity == true) {
            String ChoosenCity = list.get(cityPosition);
            city_searchBar.setText(ChoosenCity);
            cityText.setText(city_searchBar.getText());




            // 나중에 도시에 따라 같이 저장 되어있는 나라를 가져와서 set 하기 ==>countryText.setText();
        }
        //이전 버튼 누르고 다시 들어오면 검색했던 도시 초기화를 위함
        else {
            city_searchBar.setText("도시를 검색해 주세요.");
            cityText.setText("도시");
        }




        //Click Search Bar -> Search City Activity로 넘어감.
      //  Button searchButton = (Button) findViewById(R.id.city_search_bar);
        city_searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent citySearchIntent = new Intent(addTravelActivity.this, SearchCityActivity.class);
                addTravelActivity.this.startActivity(citySearchIntent);
            }
        });

        //Click nextButton
        Button nextButton = (Button) findViewById(R.id.nextButton);
        //도시 검색을 했을 경우에만 동작 하도록 하기 위함.
        if (clickedCity == true) {
            nextButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            nextButton.setTextColor(getResources().getColor(R.color.colorWhite));
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent addTravel2Intent = new Intent(addTravelActivity.this, addTravel2Activity.class);
                    addTravel2Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    addTravelActivity.this.startActivity(addTravel2Intent);
                }
            });
        }

        Button previousButton = (Button) findViewById(R.id.previousButton);
        //Click previousButton
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedCity = false; //이전 버튼 누르고 다시 들어오면 검색했던 도시의 초기화를 위함.
                Intent mainIntent = new Intent(addTravelActivity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravelActivity.this.startActivity(mainIntent);
            }
        });

        ImageButton closeButton = (ImageButton) findViewById(R.id.closeButton);
        //Click closeButton
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedCity = false; //이전 버튼 누르고 다시 들어오면 검색했던 도시의 초기화를 위함.
                Intent mainIntent = new Intent(addTravelActivity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                addTravelActivity.this.startActivity(mainIntent);
            }
        });

    }


}