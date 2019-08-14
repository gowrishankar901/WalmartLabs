package com.walmart.walmartlabstest.shared;

import com.walmart.walmartlabstest.ProductDetailsActivity;
import com.walmart.walmartlabstest.ProductsListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract ProductsListActivity contributesProductsListActivity();

    @ContributesAndroidInjector
    abstract ProductDetailsActivity contributesProductDetailsActivity();
}
