package com.example.dongh.grmr.Fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dongh.grmr.R;

public class f_matching extends Fragment {
    CustomView myView;
    Thread myThread;
    MyRunnable myRunnable;
    ImageView img1,img2,img3;
    Boolean b_img1,b_img2,b_img3;
    Boolean match_start=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_matching, container, false);

        myView = rootView.findViewById(R.id.myView);
        img1 = rootView.findViewById(R.id.ladder_img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_img1 = true;
            }
        });
        img2 = rootView.findViewById(R.id.ladder_img2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_img2 = true;
            }
        });
        img3 = rootView.findViewById(R.id.ladder_img3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_img3 = true;
            }
        });

        startSubThread();
        return rootView;
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

    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            if (match_start == true){
                while (mainHandler != null) {
                    try {
                        match_start = false;
                        if (b_img1 == true) {
                            if (myView.i < 550)
                                myView.i = myView.i + 2.0f;
                            else if ((myView.i > 550) || (myView.j < 370))
                                myView.j = myView.j + 2.0f;
                            else if ((myView.j > 370) || (myView.k < 250))
                                myView.k = myView.k + 2.0f;
                            else if ((myView.k > 250) || (myView.l < 370))
                                myView.l = myView.l + 2.0f;
                            else if (myView.m < 650)
                                myView.m = myView.m + 2.0f;
                        }


                        Message msg = Message.obtain();
                        msg.what = 0;
                        mainHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
