package com.example.syl.grmr.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.syl.grmr.R;

public class pw_find1 extends Fragment {

    Login2Activity activity;
    LinearLayout find_pw;
    Button fr_back;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (Login2Activity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pw_find1_fragment, container, false);

        find_pw=(LinearLayout)view.findViewById(R.id.findpw);
        find_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(1);
            }
        });

        fr_back=(Button)view.findViewById(R.id.fr_back1);
        fr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backintent = new Intent(getActivity(),LoginActivity.class);
                startActivity(backintent);
            }
        });
        return view;
    }

}
