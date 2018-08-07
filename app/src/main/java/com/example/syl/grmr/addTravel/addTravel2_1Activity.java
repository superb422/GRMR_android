package com.example.syl.grmr.addTravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.syl.grmr.Adapter.CityListviewAdapter.clickedCity;


public class addTravel2_1Activity extends AppCompatActivity{
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date day1;
    Date day2;
    static public TextView homeComingDate;
    static boolean clickedDate = false;
    // static public TextView homeComingDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel2_1);

        /********************************Bring Text Info*******************************/
        //Bring the City Name
        TextView cityText = (TextView) findViewById(R.id.cityText);
        cityText.setText(addTravelActivity.cityText.getText());
        //Bring the departure date
        final TextView departureText = (TextView) findViewById(R.id.departureText);
        departureText.setText(addTravel2Activity.departureDate.getText());

        final String str_departure = addTravel2Activity.departureDate.getText().toString();
        /***************************************************************/

        //Bring the calendar date
        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
        homeComingDate = (TextView)findViewById(R.id.homeComingText);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedDate = true;
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Log.d("출국날짜 > ", str_departure);
                Log.d("귀국날짜 > ", String.valueOf(year) + '-' + String.valueOf(month+1) + '-' + String.valueOf(dayOfMonth));
                //날짜 비교 위해 format에 날짜 집어넣음.
                try {
                    day1 = format.parse(str_departure);
                    day2 = format.parse(String.valueOf(year) +"-"+ String.valueOf(month+1) +"-"+ String.valueOf(dayOfMonth));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //(compare > 0 / day1>day2),(compare < 0 / day1<day2),(compare = 0 / day1=day2)
                int compare = day1.compareTo(day2);

                //귀국 날짜가 출국 날짜보다 이전이면 Next버튼 클릭 안 됨.
                if(compare <= 0){
                        homeComingDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        Toast.makeText(addTravel2_1Activity.this, "" + year + "/" + (month + 1) + "/" + dayOfMonth, 0).show();


                        /****Click nextButton //날짜 클릭 안할 시, 다음으로 못 넘어감.******/
                        Button nextButton = (Button) findViewById(R.id.nextButton);
                        nextButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        nextButton.setTextColor(getResources().getColor(R.color.colorWhite));
                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent addTravel3Intent = new Intent(addTravel2_1Activity.this, addTravel3Activity.class);
                                addTravel3Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                addTravel2_1Activity.this.startActivity(addTravel3Intent);
                            }
                        });
                        /**********************************************************************/
                }

            }
        });


        /*************************************Button Handler**********************************************/
        //Click previousButton
        Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravelIntent = new Intent(addTravel2_1Activity.this, addTravel2Activity.class);
                addTravelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravel2_1Activity.this.startActivity(addTravelIntent);
            }
        });

        //Click closeButton
        ImageButton closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedCity = false;
                Intent mainIntent = new Intent(addTravel2_1Activity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                addTravel2_1Activity.this.startActivity(mainIntent);
            }
        });
        /***********************************************************************************/

    }
}
