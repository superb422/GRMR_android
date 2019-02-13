package com.example.dongh.grmr.Matching_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dongh.grmr.Matching_register.Constructor.matching_check;
import com.example.dongh.grmr.R;
import com.example.dongh.grmr.Matching_register.CalendarCore.AirMonthAdapter;
import com.example.dongh.grmr.Matching_register.CalendarCore.CalendarUtil.AirCalendarUtils;
import com.example.dongh.grmr.Matching_register.CalendarCore.DatePickerController;
import com.example.dongh.grmr.Matching_register.CalendarCore.DayPickerView;
import com.example.dongh.grmr.Matching_register.CalendarCore.SelectModel;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Travel_Schedule extends AppCompatActivity implements DatePickerController {

    ImageView exit;

    public final static String EXTRA_FLAG = "FLAG";
    public final static String EXTRA_IS_BOOIKNG = "IS_BOOING";
    public final static String EXTRA_IS_SELECT = "IS_SELECT";
    public final static String EXTRA_BOOKING_DATES = "BOOKING_DATES";
    public final static String EXTRA_SELECT_DATE_SY = "SELECT_START_DATE_Y";
    public final static String EXTRA_SELECT_DATE_SM = "SELECT_START_DATE_M";
    public final static String EXTRA_SELECT_DATE_SD = "SELECT_START_DATE_D";
    public final static String EXTRA_SELECT_DATE_EY = "SELECT_END_DATE_Y";
    public final static String EXTRA_SELECT_DATE_EM = "SELECT_END_DATE_M";
    public final static String EXTRA_SELECT_DATE_ED = "SELECT_END_DATE_D";
    public final static String EXTRA_IS_MONTH_LABEL = "IS_MONTH_LABEL";
    public final static String EXTRA_IS_SINGLE_SELECT = "IS_SINGLE_SELECT";
    public final static String EXTRA_ACTIVE_MONTH_NUM = "ACTIVE_MONTH_NUMBER";
    public final static String EXTRA_MAX_YEAR = "MAX_YEAR";

    private DayPickerView pickerView;
    private RelativeLayout rl_done_btn;
    private TextView duration_text;

    private String SELECT_START_DATE = "";
    private String SELECT_END_DATE = "";
    private long DURATION;
    private int BASE_YEAR = 2018;

    private String FLAG = "all";
    private boolean isSelect = false;
    private boolean isBooking = false;
    private boolean isMonthLabel = false;
    private boolean isSingleSelect = false;
    private ArrayList<String> dates;
    private SelectModel selectDate;

    private int sYear = 0;
    private int sMonth = 0;
    private int sDay = 0;
    private int eYear = 0;
    private int eMonth = 0;
    private int eDay = 0;

    private int maxActivieMonth = -1;
    private int maxYear = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        exit = (ImageView)findViewById(R.id.schedule_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent getData = getIntent();
        FLAG = getData.getStringExtra(EXTRA_FLAG) != null ? getData.getStringExtra(EXTRA_FLAG):"all";
        isBooking = getData.getBooleanExtra(EXTRA_IS_BOOIKNG , false);
        isSelect = getData.getBooleanExtra(EXTRA_IS_SELECT , false);
        isMonthLabel = getData.getBooleanExtra(EXTRA_IS_MONTH_LABEL , false);
        isSingleSelect = getData.getBooleanExtra(EXTRA_IS_SINGLE_SELECT , false);
        dates = getData.getStringArrayListExtra(EXTRA_BOOKING_DATES);
        maxActivieMonth = getData.getIntExtra(EXTRA_ACTIVE_MONTH_NUM , -1);
        maxYear = getData.getIntExtra(EXTRA_MAX_YEAR , -1);

        sYear = getData.getIntExtra(EXTRA_SELECT_DATE_SY , 0);
        sMonth = getData.getIntExtra(EXTRA_SELECT_DATE_SM , 0);
        sDay = getData.getIntExtra(EXTRA_SELECT_DATE_SD , 0);

        eYear = getData.getIntExtra(EXTRA_SELECT_DATE_EY , 0);
        eMonth = getData.getIntExtra(EXTRA_SELECT_DATE_EM , 0);
        eDay = getData.getIntExtra(EXTRA_SELECT_DATE_ED , 0);

        if(sYear == 0 || sMonth == 0 || sDay == 0
                || eYear == 0 || eMonth == 0 || eDay == 0){
            selectDate = new SelectModel();
            isSelect = false;
        }


        init();

    }

    private void init(){

        pickerView = findViewById(R.id.pickerView);
        pickerView.setIsMonthDayLabel(isMonthLabel);
        pickerView.setIsSingleSelect(isSingleSelect);
        pickerView.setMaxActiveMonth(maxActivieMonth);

        // 현재 연도
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy", Locale.KOREA );
        Date currentTime = new Date ( );
        String dTime = formatter.format ( currentTime );

        if(maxYear != -1 && maxYear > Integer.parseInt(new DateTime().toString("yyyy"))){
            BASE_YEAR = maxYear;
        }else{
            // default : now year + 2 year
            // 최대 년 수 가 찍힐 수 있는 변수
            BASE_YEAR = Integer.valueOf(dTime) + 2;
        }

        if(dates != null && dates.size() != 0 && isBooking){
            pickerView.setShowBooking(true);
            pickerView.setBookingDateArray(dates);
        }

        if(isSelect){
            selectDate = new SelectModel();
            selectDate.setSelectd(true);
            selectDate.setFristYear(sYear);
            selectDate.setFristMonth(sMonth);
            selectDate.setFristDay(sDay);
            selectDate.setLastYear(eYear);
            selectDate.setLastMonth(eMonth);
            selectDate.setLastDay(eDay);
            pickerView.setSelected(selectDate);
        }

        pickerView.setController(this);


//        rl_done_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if((SELECT_START_DATE == null || SELECT_START_DATE.equals("")) && (SELECT_END_DATE == null || SELECT_END_DATE.equals(""))){
//                    SELECT_START_DATE = "";
//                    SELECT_END_DATE = "";
//                }else{
//
//                }
//
//                //Toast.makeText(getApplicationContext(), "Select Date range : " +SELECT_START_DATE + " ~ " + SELECT_END_DATE, Toast.LENGTH_SHORT).show();
//
//                //Toast.makeText(getApplicationContext(), "Select Date range : " +SELECT_START_DATE + " ~ " + SELECT_END_DATE, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getMaxYear() {
        return BASE_YEAR;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        // TODO Single Select Event
        try{
            String start_month_str =  String.format("%02d" , (month+1));
            // 일
            String start_day_str =  String.format("%02d" , day);
            String startSetDate = year+start_month_str+start_day_str;

            String startDateDay = AirCalendarUtils.getDateDay(startSetDate , "yyyyMMdd");
            SELECT_END_DATE = "";
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDateRangeSelected(AirMonthAdapter.SelectedDays<AirMonthAdapter.CalendarDay> selectedDays) {

        try{
            Calendar cl = Calendar.getInstance();

            cl.setTimeInMillis(selectedDays.getFirst().getDate().getTime());

            // 월
            int start_month_int = (cl.get(Calendar.MONTH)+1);
            String start_month_str =  String.format("%02d" , start_month_int);

            // 일
            int start_day_int = cl.get(Calendar.DAY_OF_MONTH);
            String start_day_str =  String.format("%02d" , start_day_int);

            String startSetDate = cl.get(Calendar.YEAR)+start_month_str+start_day_str;
            String startDateDay = AirCalendarUtils.getDateDay(startSetDate , "yyyyMMdd");
            String startDate = cl.get(Calendar.YEAR) + "-" + start_month_str + "-" + start_day_str;


            Calendar baseCal = new GregorianCalendar(cl.get(Calendar.YEAR),start_month_int,start_day_int);

            cl.setTimeInMillis(selectedDays.getLast().getDate().getTime());

            // 월
            int end_month_int = (cl.get(Calendar.MONTH)+1);
            String end_month_str = String.format("%02d" , end_month_int);

            // 일
            int end_day_int = cl.get(Calendar.DAY_OF_MONTH);
            String end_day_str = String.format("%02d" , end_day_int);

            String endSetDate = cl.get(Calendar.YEAR)+end_month_str+end_day_str;
            String endDateDay = AirCalendarUtils.getDateDay(endSetDate , "yyyyMMdd");
            String endDate = cl.get(Calendar.YEAR) + "-" + end_month_str + "-" + end_day_str;

            Calendar targetCal = new GregorianCalendar(cl.get(Calendar.YEAR),end_month_int,end_day_int);

            long diffSec = (targetCal.getTimeInMillis() - baseCal.getTimeInMillis())/1000;
            long diffDays = diffSec / (24*60*60);

            SELECT_START_DATE = startDate;
            SELECT_END_DATE = endDate;
            DURATION = diffDays;

            duration_text = findViewById(R.id.duration);
            rl_done_btn = findViewById(R.id.rl_done_btn);

            if(DURATION!=0) {
                String duration_str1 = String.valueOf(DURATION);
                String duration_str2 = String.valueOf(DURATION + 1);
                rl_done_btn.setBackgroundResource(R.drawable.ic_schedulebtn);
                duration_text.setText(duration_str1 + "박 " + duration_str2 + "일의 여행등록");

                rl_done_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent scheduleIntent = new Intent(Travel_Schedule.this,matching_check.class);
                        startActivity(scheduleIntent);
                    }
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
