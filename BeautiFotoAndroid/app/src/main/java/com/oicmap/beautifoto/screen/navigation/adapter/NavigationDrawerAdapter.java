package com.oicmap.beautifoto.screen.navigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.screen.navigation.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    int currentSelectedIndex = 0;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.title.setTextColor(context.getResources().getColorStateList(R.color.xml_color_text_nav));
        if(currentSelectedIndex == position){
            holder.title.setTextColor(context.getResources().getColor(R.color.colorTextActive));
            holder.title.setBackgroundResource(R.color.colorBgActive);
        }else{
            holder.title.setTextColor(context.getResources().getColor(R.color.colorTextNormal));
            holder.title.setBackgroundResource(R.color.colorBgNormal);
        }
    }

    public void setCurrentSelectedIndex(int index){
        this.currentSelectedIndex = index;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}