package com.example.syl.grmr.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.syl.grmr.R;
import com.example.syl.grmr.addTravel.addTravel3Activity;

import java.util.List;

import static com.example.syl.grmr.addTravel.SearchFriendActivity.searchText;

public class FriendListviewAdapter extends BaseAdapter {

    static public SpannableStringBuilder builder;
    private Context context;
    static public List<String> list;
    private LayoutInflater inflate;
    static public FriendListviewAdapter.ViewHolder viewHolder;
    static public boolean clickedFriend = false; //친구가 눌리고 안눌리고에 따라 addTravelActivity에서 다르게 동작하는 기능이 있기 때문에 지정.
    static public View convert;
    static public int friendPosition; //리스트 클릭했을 때의 그 position 값

    public FriendListviewAdapter(List<String> list, Context context) {

        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convert = convertView;
        final int Position = position;

        if (convertView == null) {
            convertView = inflate.inflate(R.layout.add_friends_list, null);


            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.friend_list);

            //리스트에 있는 데이터를 클릭했을 때
            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent addTravelIntent = new Intent(context, addTravel3Activity.class);
                    addTravelIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    friendPosition=Position; //final Position 값을 public static에 넣어서 다른 클래스에서 사용하기 위함.
                    clickedFriend = true;
                    context.startActivity(addTravelIntent);

                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FriendListviewAdapter.ViewHolder) convertView.getTag();

        }

        // 리스트에 있는 데이터와 입력단어가 일치하는 부분의 색깔을 변경한다.
        String coloredText = list.get(position);
        builder = new SpannableStringBuilder(coloredText);
        //builder.setSpan(new ForegroundColorSpan(Color.parseColor("#f16461")), 0, searchText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //리스트를 뿌린다.
        viewHolder.label.setText(builder);

        return convertView;
    }


    class ViewHolder {
        public TextView label;
    }
}
