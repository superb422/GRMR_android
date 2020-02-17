package com.example.dongh.grmr.Fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * 뷰를 상속하여 직접 만든 뷰
 * 
 * @author Mike
 *
 */
public class CustomView extends View {

	float i,j,k,l,m;
	public CustomView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint pnt = new Paint();
		pnt.setStrokeWidth(6f);
		pnt.setColor(Color.parseColor("#c7c7c7"));
		pnt.setStyle(Paint.Style.STROKE);
		/* 세로 선*/
		canvas.drawLine(350, 620, 350, 1750, pnt);
		canvas.drawLine(720, 620, 720, 1750, pnt);
		canvas.drawLine(1090, 620, 1090, 1750, pnt);

		/* 가로 선*/
		canvas.drawLine(350,900,720,900,pnt);
		canvas.drawLine(720,1150,1090,1150,pnt);
		canvas.drawLine(350,1400,720,1400,pnt);


		Paint pnt_text = new Paint();
		pnt_text.setTextSize(50);
		pnt_text.setColor(Color.rgb(0,0,255));
		pnt_text.setAntiAlias(true);
		pnt_text.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.NORMAL));

		canvas.drawText("- 여행지가 같으며 이성으로 이루어진 팀과 매칭됩니다",200,2095,pnt_text);
		canvas.drawText("- 하루에 3번 사다리매칭이 가능합니다.",200,2165,pnt_text);

		pnt.setStrokeWidth(6f);
		pnt.setColor(Color.parseColor("#000000"));
		pnt.setStyle(Paint.Style.STROKE);
		if (i>50) { // 사진 클릭 시 검은색 선이 보이는 것을 방지
			canvas.drawLine(350, 620, 350, 350 + i, pnt);
			canvas.drawLine(350, 900, 350 + j, 900, pnt);
			canvas.drawLine(720, 900, 720, 900 + k, pnt);
			canvas.drawLine(720, 1150, 720 + l, 1150, pnt);
			canvas.drawLine(1090, 1150, 1090, 1150 + m, pnt);
		}


	}



}

