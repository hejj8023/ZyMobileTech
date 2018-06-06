package com.example.wanandroid.manager;

import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.example.wanandroid.Const;
import com.example.wanandroid.R;
import com.zysdk.vulture.clib.glide.GlideApp;
import com.zysdk.vulture.clib.utils.UiUtils;

/**
 * Created by example on 2018/4/11.
 */

public class GlideLoaderManager {
    public static void loadImage(ImageView imageView, String url, int type) {
        RequestOptions requestOptions = null;
        switch (type) {
            case Const.IMAGE_LOADER.HEAD_IMG:
                requestOptions = RequestOptions.placeholderOf(R.drawable.iv_img_default).centerCrop();
                break;
            case Const.IMAGE_LOADER.NOMAL_IMG:
                requestOptions = RequestOptions.placeholderOf(R.mipmap.ic_launcher_round).centerCrop();
                break;
        }
        if (requestOptions != null) {
            GlideApp.with(UiUtils.getContext()).load(url).apply(requestOptions).into(imageView);
        }
    }
}
