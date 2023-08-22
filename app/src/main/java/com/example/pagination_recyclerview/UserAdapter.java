package com.example.pagination_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<userobject>mUserobjects;
    public void setdata(List<userobject> list){
        this.mUserobjects=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        userobject userobject =mUserobjects.get(position);

        if(userobject==null){
            return;
        }
        holder.txtname.setText(userobject.getName()+""+(position+1));

    }

    @Override
    public int getItemCount() {
        if(mUserobjects!=null){
            return mUserobjects.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView) itemView.findViewById(R.id.txtuser);
        }
    }
}
