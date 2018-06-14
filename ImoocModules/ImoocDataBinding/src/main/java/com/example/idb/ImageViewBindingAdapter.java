package com.example.idb;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.zysdk.vulture.clib.glide.GlideApp;

public class ImageViewBindingAdapter {

    @BindingAdapter({"app:imgUrl", "app:placeHolder"})
    public static void loadImageByUri(ImageView imageView, String imgUrl, Drawable placeholder) {
        GlideApp.with(imageView.getContext())
                .load(imgUrl)
                .placeholder(placeholder)
                .into(imageView);
    }
}
