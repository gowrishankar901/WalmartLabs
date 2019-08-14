package com.walmart.walmartlabstest;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.walmart.walmartlabstest.databinding.ActivityProductDetailsBinding;
import com.walmart.walmartlabstest.viewmodel.ProductDetailsViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ProductDetailsActivity extends AppCompatActivity {

    @Inject
    public ProductDetailsViewModel productDetailsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityProductDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        binding.setViewModel(productDetailsViewModel);
        binding.productDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}
