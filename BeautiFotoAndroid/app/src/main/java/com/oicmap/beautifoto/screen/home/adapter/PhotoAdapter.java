package com.oicmap.beautifoto.screen.home.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.util.UILHelper;
import com.oicmap.beautifoto.config.GlobalStorage;
import com.oicmap.beautifoto.model.Photo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Context context;

    // The items to display in your RecyclerView 
    private List<Photo> list;
    // Allows to remember the last item shown on screen 
    private int lastPosition = -1;

    public PhotoAdapter(Context context, List<Photo> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(list.get(position));
        // Here you apply the animation when the view is bound
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated 
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // You need to retrieve the container (ie the root ViewGroup from your custom_item_layout)
        // It's the view that will be animated
        @Bind(R.id.container)
        public FrameLayout container;

        @Bind(R.id.imvPhoto)
        public ImageView imvPhoto;

        @Bind(R.id.card_view)
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Photo photo) {
            int height = photo.height/photo.width* GlobalStorage.SCREEN.width();
            cardView.setMinimumHeight(height);
            cardView.requestLayout();
            Log.e("set photo height:","height = "+height);
            UILHelper.getInstance().displayPhoto(photo.url, imvPhoto);
        }
    }
} 