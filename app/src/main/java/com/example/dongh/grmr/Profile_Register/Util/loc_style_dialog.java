package com.example.dongh.grmr.Profile_Register.Util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dongh.grmr.R;

import java.util.ArrayList;
import java.util.List;

public class loc_style_dialog extends Dialog implements View.OnClickListener{


    public StyleDialogListener styleDialogListener;
    public List style_loc_list = new ArrayList();
    ImageView [] style_locs = new ImageView[10];
    String fileName1;
    boolean [] btn_clicked = new boolean[10];
    private Context context;
    int select_num=0;
    Button style_loc_btn;


    public loc_style_dialog(Context context) {
        super(context);
        this.context=context;
    }

    public interface StyleDialogListener{
        void onSelectedStyle(List loc_list);
    }

    //호출할 리스너 초기화
    public void StyleDialogListener(StyleDialogListener styleDialogListener){
        this.styleDialogListener = styleDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.

        setContentView(R.layout.loc_style_dialog);
        style_loc_btn=(Button) findViewById(R.id.loc_style_btn);

        for(int i=1;i< style_locs.length;i++)
        {
            fileName1 = "loc"+i;

            style_locs[i] = (ImageView) findViewById(context.getResources().getIdentifier(fileName1, "id", context.getPackageName()));
            style_locs[i].setOnClickListener(this);

            btn_clicked[i]=true;
        }
        style_loc_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        for (int i = 1; i < style_locs.length; i++) {
            if (view.getId() == style_locs[i].getId()) {

                if (btn_clicked[i] == true) {  // 선호 장소 클릭 시
                    switch (i) {
                        case 1:
                            style_locs[i].setImageResource(R.drawable.ic_style_cafe_clicked);
                            style_loc_list.add("카페");
                            break;
                        case 2:
                            style_locs[i].setImageResource(R.drawable.ic_style_pub_clicked);
                            style_loc_list.add("펍");
                            break;
                        case 3:
                            style_locs[i].setImageResource(R.drawable.ic_style_sea_clicked);
                            style_loc_list.add("바다");
                            break;
                        case 4:
                            style_locs[i].setImageResource(R.drawable.ic_style_mtn_clicked);
                            style_loc_list.add("산");
                            break;
                        case 5:
                            style_locs[i].setImageResource(R.drawable.ic_style_park_clicked);
                            style_loc_list.add("공원");
                            break;
                        case 6:
                            style_locs[i].setImageResource(R.drawable.ic_style_lake_clicked);
                            style_loc_list.add("호수");
                            break;
                        case 7:
                            style_locs[i].setImageResource(R.drawable.ic_style_temapark_clicked);
                            style_loc_list.add("테마파크");
                            break;
                        case 8:
                            style_locs[i].setImageResource(R.drawable.ic_style_exhibition_clicked);
                            style_loc_list.add("전시회");
                            break;
                        case 9:
                            style_locs[i].setImageResource(R.drawable.ic_style_photozone_clicked);
                            style_loc_list.add("포토존");
                    }

                    select_num++;
                    btn_clicked[i] = false;

                } else { // 선호 장소 취소 시

                    switch (i) {
                        case 1:
                            style_locs[i].setImageResource(R.drawable.ic_style_cafe);
                            style_loc_list.remove("카페");
                            break;
                        case 2:
                            style_locs[i].setImageResource(R.drawable.ic_style_pub);
                            style_loc_list.remove("펍");
                            break;
                        case 3:
                            style_locs[i].setImageResource(R.drawable.ic_style_sea);
                            style_loc_list.remove("바다");
                            break;
                        case 4:
                            style_locs[i].setImageResource(R.drawable.ic_style_mtn);
                            style_loc_list.remove("산");
                            break;
                        case 5:
                            style_locs[i].setImageResource(R.drawable.ic_style_park);
                            style_loc_list.remove("공원");
                            break;
                        case 6:
                            style_locs[i].setImageResource(R.drawable.ic_style_lake);
                            style_loc_list.remove("호수");
                            break;
                        case 7:
                            style_locs[i].setImageResource(R.drawable.ic_style_temapark);
                            style_loc_list.remove("테마파크");
                            break;
                        case 8:
                            style_locs[i].setImageResource(R.drawable.ic_style_exhibition);
                            style_loc_list.remove("전시회");
                            break;
                        case 9:
                            style_locs[i].setImageResource(R.drawable.ic_style_photozone);
                            style_loc_list.remove("포토존");
                    }
                    select_num--;
                    btn_clicked[i] = true;
                }

                if (select_num > 2) {
                    style_loc_btn.setBackgroundResource(R.drawable.btn_skyblue);
                    style_loc_btn.setTextColor(Color.rgb(255, 255, 255));

                } else {
                    style_loc_btn.setBackgroundResource(R.drawable.btn_gray);
                    style_loc_btn.setTextColor(Color.rgb(151, 151, 151));
                }


            }
        }
        if ((view.getId() == style_loc_btn.getId())&& select_num>2 &&select_num<6) {
            styleDialogListener.onSelectedStyle(style_loc_list);
            dismiss();
        }
    }

}
