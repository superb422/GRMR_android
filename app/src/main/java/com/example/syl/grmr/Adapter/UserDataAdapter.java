package com.example.syl.grmr.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syl.grmr.Constructor.User;
import com.example.syl.grmr.NavigationMenu.FriendProfile;
import com.example.syl.grmr.R;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {
    public List<User> Users;

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView Image;

        public UserViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.User_name);
            Image = (ImageView)view.findViewById(R.id.User_image);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext() , FriendProfile.class);
            view.getContext().startActivity(intent);
        }
    }

    public UserDataAdapter(List<User> Users) {
        this.Users = Users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User User = Users.get(position);
        holder.name.setText(User.getName());
        holder.Image.setImageResource(User.getImage());
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }
}
