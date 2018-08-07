package com.example.syl.grmr.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.syl.grmr.R;

public class pw_find4 extends Fragment {
    Login2Activity activity;
    Button modify,fr_back;

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
        view = inflater.inflate(R.layout.pw_find4_fragment, container, false);

        modify=(Button)view.findViewById(R.id.modifybutton);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });

        fr_back=(Button)view.findViewById(R.id.fr_back4);
        fr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });

        return view;


    }

}
