package com.walmart.walmartlabstest;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.walmart.walmartlabstest.serviceprovider.GlideProvider;

public class ViewBindingAdapter {

    @BindingAdapter("onPageScroll")
    public static void onPageScrolled(RecyclerView recyclerview, final OnPageScrollListener onPageScrollListener) {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 1 && recyclerView.getScrollState() != 2) {
                    onPageScrollListener.loadMoreProducts();
                }
            }
        });
    }

    @FunctionalInterface
    public interface OnPageScrollListener {
        void loadMoreProducts();
    }

    @BindingAdapter({"loadImageUrl", "provider"})
    public static void setImageViewResource(ImageView imageView, String imageUrl, GlideProvider glideProvider) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            glideProvider.loadImage(imageUrl).into(imageView);
        }
    }
}
