package com.example.syl.grmr.addTravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syl.grmr.Main.MainActivity;
import com.example.syl.grmr.R;

import static com.example.syl.grmr.Adapter.CityListviewAdapter.clickedCity;


public class addTravel2Activity extends AppCompatActivity {
    static public TextView departureDate;
    static boolean clickedDate = false;

    // static public TextView homeComingDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel2);

        /*****************************Bring Text Info**********************************/
        //Bring the City Name
        TextView cityText = (TextView) findViewById(R.id.cityText);
        cityText.setText(addTravelActivity.cityText.getText());
        /**************************************************************/

        //Bring the calendar date
        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
        departureDate = (TextView) findViewById(R.id.departureText);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                departureDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                Toast.makeText(addTravel2Activity.this, "" + year + "/" + (month + 1) + "/" + dayOfMonth, 0).show();

                /****Click nextButton //날짜 클릭 안할 시, 다음으로 못 넘어감.******/
                Button nextButton = (Button) findViewById(R.id.nextButton);
                nextButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                nextButton.setTextColor(getResources().getColor(R.color.colorWhite));
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addTravel2_1Intent = new Intent(addTravel2Activity.this, addTravel2_1Activity.class);
                        addTravel2_1Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        addTravel2Activity.this.startActivity(addTravel2_1Intent);
                    }
                });
                /**********************************************************************/
            }

        });


        /*************************************Button Handler**********************************************/
        //Click previousButton
        Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTravelIntent = new Intent(addTravel2Activity.this, addTravelActivity.class);
                addTravelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                addTravel2Activity.this.startActivity(addTravelIntent);
            }
        });

        //Click closeButton
        ImageButton closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedCity = false;
                Intent mainIntent = new Intent(addTravel2Activity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                addTravel2Activity.this.startActivity(mainIntent);
            }
        });
        /***********************************************************************************/
    }
}
