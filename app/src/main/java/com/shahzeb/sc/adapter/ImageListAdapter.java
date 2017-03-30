package com.shahzeb.sc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahzeb.sc.R;
import com.shahzeb.sc.model.Response.Image;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImagesViewHolder> {

    Context ctx;
    List<Image> imageList;

    public ImageListAdapter(Context context, List<Image> imageList) {
        this.ctx = context;
        this.imageList = imageList;
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_caption)
        TextView tvCaption;


        public ImagesViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_image_row, null);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {

        holder.tvId.setText(imageList.get(position).id);
        holder.tvTitle.setText(imageList.get(position).title);
        holder.tvCaption.setText(imageList.get(position).caption);

    }

    @Override
    public int getItemCount() {
        return (imageList != null ? imageList.size():0);
    }
}
