package com.shahzeb.sc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahzeb.sc.model.Response.Image;
import com.shahzeb.sc.utils.SCUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_caption)
    TextView tvCaption;

    private Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            image = getIntent().getParcelableExtra(SCConstants.KEY_IMAGE);
        }

        if (image.display_sizes != null) {
            Picasso.with(this)
                    .load(image.display_sizes.get(0).uri)
                    .resize(SCUtil.getDeviceWidth(this), SCUtil.getDeviceWidth(this))
                    .centerCrop()
                    .into(imageView);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }

        tvTitle.setText(image.title);
        tvCaption.setText(image.caption);
    }
}
