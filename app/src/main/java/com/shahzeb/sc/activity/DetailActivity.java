package com.shahzeb.sc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahzeb.sc.R;
import com.shahzeb.sc.SCConstants;
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
            String url = image.display_sizes.get(0).uri;
            String trimmedUrl = url.substring(0, url.indexOf("?"));
                    Picasso.with(this)
                            .load(trimmedUrl)
                            .placeholder(R.drawable.blurred_background)
                            .resize(SCUtil.getDeviceWidth(this), SCUtil.getDeviceWidth(this))
                            .centerCrop()
                            .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.blurred_background);
        }

        tvTitle.setText(image.title);
        tvCaption.setText(image.caption);
    }
}
