package com.example.dongh.grmr.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.dongh.grmr.R;

public class LadderMatching extends AppCompatActivity {
    CustomView myView;
    Thread myThread;
    MyRunnable myRunnable;
    ImageView img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_matching);
        myView = (CustomView)findViewById(R.id.myView);

        img1 = (ImageView)findViewById(R.id.ladder_img1);
        img2 = (ImageView)findViewById(R.id.ladder_img2);
        img3 = (ImageView)findViewById(R.id.ladder_img3);
        startSubThread();
    }
    public void startSubThread()
    {
        myRunnable = new MyRunnable();
        myThread = new Thread(myRunnable);
        myThread.setDaemon(true);
        myThread.start();
    }

    android.os.Handler mainHandler = new android.os.Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 0)
            {
                myView.invalidate();
            }
        }
    };


    public class MyRunnable implements Runnable
    {
        @Override
        public void run()
        {
            while(mainHandler!=null)
            {
                try
                {
                    if(myView.i<550)
                        myView.i = myView.i + 3.5f;
                    else if ((myView.i>550)||(myView.j<370))
                        myView.j = myView.j + 3.5f;
                    else if ((myView.j>370)||(myView.k<250))
                        myView.k = myView.k + 3.5f;
                    else if ((myView.k>250)||(myView.l<370))
                        myView.l = myView.l + 3.5f;
                    else if (myView.m<650)
                        myView.m = myView.m + 3.5f;


                    Message msg = Message.obtain();
                    msg.what = 0;
                    mainHandler.sendMessage(msg);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                try
                {
                    Thread.sleep(10);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
