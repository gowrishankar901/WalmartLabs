package com.walmart.walmartlabstest.serviceprovider;

import android.content.Context;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

public class GlideProvider {

    private final Context context;

    public GlideProvider(Context context) {
        this.context = context;
    }

    public DrawableTypeRequest loadImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            return Glide.with(context).load(imageUrl);
        }
        return null;
    }
}
