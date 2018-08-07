package com.example.syl.grmr.Main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import com.example.syl.grmr.Constructor.RecyclerItem;
import com.example.syl.grmr.R;



public class Matching_Chatting_Adapter extends RecyclerView.Adapter<Matching_Chatting_Adapter.Holder> {
    private Context context;
    private List<RecyclerItem> list;

    public Matching_Chatting_Adapter(Context context, List<RecyclerItem> list) {
        this.context = context;
        this.list = list;
    }

    // ViewHolder 생성
    // row layout을 화면에 뿌려주고 holder에 연결
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matching_chatting_list, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    /*
     * Todo 만들어진 ViewHolder에 data 삽입 ListView의 getView와 동일
     *
     * */
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 각 위치에 문자열 세팅
        holder.mNameTv.setText(list.get(position).getCity());
        holder.departureDate.setText(list.get(position).getDepartureDate());
        holder.homecomingDate.setText(list.get(position).getHomecomingDate());

    }

    // 몇개의 데이터를 리스트로 뿌려줘야하는지 반드시 정의해줘야한다
    @Override
    public int getItemCount() {
        return list.size(); // RecyclerView의 size return
    }

    // ViewHolder는 하나의 View를 보존하는 역할을 한다
    public class Holder extends RecyclerView.ViewHolder{
        private TextView mNameTv;
        private TextView departureDate;
        private TextView homecomingDate;
        public Holder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.itemNameTv);
            departureDate = (TextView) itemView.findViewById(R.id.departureDate);
            homecomingDate = (TextView) itemView.findViewById(R.id.homecomingDate);
        }

    }



}
