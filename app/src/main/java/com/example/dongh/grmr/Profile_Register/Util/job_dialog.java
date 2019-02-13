package com.example.dongh.grmr.Profile_Register.Util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dongh.grmr.R;

public class job_dialog extends Dialog implements View.OnClickListener{

    Button negativeButton,positiveButton;
    EditText d_job_txt;
    TextView job_txt;
    public JobDialogListener jobDialogListener;

    public job_dialog (Context context) {
        super(context);

        /*job_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setJob(d_job_txt.getText().toString());
                dismiss();
            }
        });*/

    }

    public interface JobDialogListener{
        void onPositiveClicked(String job);
        void onNegativeClicked();
    }

    //호출할 리스너 초기화
    public void JobDialogListener(JobDialogListener jobDialogListener){
        this.jobDialogListener = jobDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.

        setContentView(R.layout.job_dialog);
        negativeButton = (Button)findViewById(R.id.job_neg);
        positiveButton = (Button)findViewById(R.id.job_pos);
        d_job_txt = (EditText)findViewById(R.id.d_job_txt); job_txt = (TextView)findViewById(R.id.job_txt);


        //init
        positiveButton = (Button)findViewById(R.id.job_pos);
        negativeButton = (Button)findViewById(R.id.job_neg);


        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.job_pos: //확인 버튼을 눌렀을 때
                //각각의 변수에 EditText에서 가져온 값을 저장
                String job = d_job_txt.getText().toString();

                //인터페이스의 함수를 호출하여 변수에 저장된 값들을 Activity로 전달
                jobDialogListener.onPositiveClicked(job);
                dismiss();
                break;
            case R.id.job_neg: //취소 버튼을 눌렀을 때
                cancel();
                break;
        }
    }

}
