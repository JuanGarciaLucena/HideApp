package com.emebesoft.hideapp.hideListView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emebesoft.hideapp.R;
import com.emebesoft.hideapp.objects.Position;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayesa1 on 04/04/2017.
 */

public class HideRecyclerViewAdapter extends RecyclerView.Adapter<HideRecyclerViewAdapter.HideViewHolder> {

    private List<Position> hideList;
    private HideRecyclerViewOnItemClickListener listener;
    private Context context;

    public HideRecyclerViewAdapter(Context context, List<Position> hideList, HideRecyclerViewOnItemClickListener listener){
        this.hideList = hideList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public HideRecyclerViewAdapter.HideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hide_recycler_view, parent, false);
        final HideViewHolder hideViewHolder = new HideViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, hideViewHolder.getAdapterPosition());
            }
        });

        return hideViewHolder;
    }

    @Override
    public void onBindViewHolder(HideRecyclerViewAdapter.HideViewHolder holder, int position) {
        holder.textViewHideItemDescription.setText("Puesto número " + hideList.get(position).getPositionName());
        Glide.with(context)
                .load(hideList.get(position).getPositionPicture())
                .placeholder(R.drawable.pic_hide)
                .into(holder.imageViewItem);
    }

    @Override
    public int getItemCount() {
        return hideList.size();
    }



    public static class HideViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textViewHideItemDescription)TextView textViewHideItemDescription;
        @BindView(R.id.imageViewItem)ImageView imageViewItem;

        public HideViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHide(){

        }



    }
}
