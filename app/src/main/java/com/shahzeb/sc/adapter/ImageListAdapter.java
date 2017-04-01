package com.shahzeb.sc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahzeb.sc.R;
import com.shahzeb.sc.model.Response.Image;
import com.shahzeb.sc.utils.SCUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImagesViewHolder> {

    private Context ctx;
    private List<Image> imageList;
    private ImageListAdapterListener listener;

    public interface ImageListAdapterListener {
        void onItemClick(ImageView imageView, Image image);
    }

    public ImageListAdapter(Context context, List<Image> imageList) {
        this.ctx = context;
        this.imageList = imageList;
        this.listener = (ImageListAdapterListener) context;
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
            listener.onItemClick(image, imageList.get(getAdapterPosition()));
        }

    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(ctx, R.layout.item_image_row, null);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {

        if (imageList.get(position).display_sizes != null) {
            Picasso.with(ctx)
                    .load(imageList.get(position).display_sizes.get(0).uri)
                    .resize(SCUtil.getDeviceWidth(ctx), SCUtil.getDeviceWidth(ctx)/2)
                    .centerCrop()
                    .into(holder.image);
        } else {
            holder.image.setImageResource(R.mipmap.ic_launcher);
        }
        holder.tvId.setText(imageList.get(position).id);
        holder.tvTitle.setText(imageList.get(position).title);
        holder.tvCaption.setText(imageList.get(position).caption);

    }

    @Override
    public int getItemCount() {
        return (imageList != null ? imageList.size():0);
    }
}
