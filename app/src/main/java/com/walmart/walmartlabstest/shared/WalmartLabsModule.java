package com.walmart.walmartlabstest.shared;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.walmart.walmartlabstest.service.ProductsListService;
import com.walmart.walmartlabstest.serviceprovider.GlideProvider;
import com.walmart.walmartlabstest.serviceprovider.WalmartLabsProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WalmartLabsModule {

    private static final String WALMART_LABS_HOST_URL = "https://mobile-tha-server.firebaseapp.com/";

    @Singleton
    @Provides
    Retrofit provideRetrofitService(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(WALMART_LABS_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create();
    }

    @Singleton
    @Provides
    ProductsListService provideProductsListService(Retrofit retrofit) {
        return retrofit.create(ProductsListService.class);
    }

    @Singleton
    @Provides
    WalmartLabsProvider provideWalmartLabsProvider(ProductsListService productsListService) {
        return new WalmartLabsProvider(productsListService);
    }

    @Singleton
    @Provides
    GlideProvider provideGlideProvider(Context context) {
        return new GlideProvider(context);
    }
}
