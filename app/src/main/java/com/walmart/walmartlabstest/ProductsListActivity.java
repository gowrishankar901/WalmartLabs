package com.walmart.walmartlabstest;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.walmart.walmartlabstest.databinding.ActivityProductsListBinding;
import com.walmart.walmartlabstest.viewmodel.ProductsListViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ProductsListActivity extends AppCompatActivity {

    @Inject
    public ProductsListViewModel productsListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ActivityProductsListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);
        binding.setViewModel(productsListViewModel);
        binding.productsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.productsRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.getViewModel().launchProductDetailsLiveData.observe(this, integer -> launchProductDetailsActivity());
    }

    private void launchProductDetailsActivity() {
        startActivity(new Intent(this,ProductDetailsActivity.class));
    }
}
