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

public class pw_find3 extends Fragment {
    Login2Activity activity;
    Button changepw,fr_back;

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
        view = inflater.inflate(R.layout.pw_find3_fragment, container, false);

        changepw=(Button)view.findViewById(R.id.changepw);
        changepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(3);
            }
        });

        fr_back=(Button)view.findViewById(R.id.fr_back3);
        fr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onFragmentChange(0);
            }
        });
        return view;
    }

}
