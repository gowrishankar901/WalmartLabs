package com.walmart.walmartlabstest.serviceprovider;

import com.walmart.walmartlabstest.model.ProductsList;
import com.walmart.walmartlabstest.service.ProductsListService;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WalmartLabsProvider {

    private final ProductsListService productsListService;

    public WalmartLabsProvider(ProductsListService productsListService) {
        this.productsListService = productsListService;
    }

    public Single<ProductsList> getProductsList(int pageNumber, int pageSize) {
        return productsListService.getProductsList(pageNumber, pageSize)
                .compose(applySchedulers());
    }

    private <T> SingleTransformer<T, T> applySchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
