package com.walmart.walmartlabstest.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.walmart.walmartlabstest.ProductsListAdapter;
import com.walmart.walmartlabstest.model.Product;
import com.walmart.walmartlabstest.model.ProductsList;
import com.walmart.walmartlabstest.serviceprovider.WalmartLabsProvider;
import com.walmart.walmartlabstest.shared.ProductUseCase;
import com.walmart.walmartlabstest.shared.TransientDataProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProductsListViewModel extends ViewModel {
    private static final int PAGE_SIZE = 20;

    public ObservableBoolean shouldShowProgressBar = new ObservableBoolean(false);
    public MutableLiveData<Integer> launchProductDetailsLiveData = new MutableLiveData<>();

    List<ProductViewModel> productViewModels = new ArrayList<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final WalmartLabsProvider walmartLabsProvider;
    private final TransientDataProvider transientDataProvider;
    private ProductsListAdapter productsListAdapter;
    private int pageNumber = 1;

    @Inject
    public ProductsListViewModel(WalmartLabsProvider walmartLabsProvider, TransientDataProvider transientDataProvider) {
        this.walmartLabsProvider = walmartLabsProvider;
        this.transientDataProvider = transientDataProvider;
        productsListAdapter = new ProductsListAdapter(productViewModels, this);
        fetchProductsList(pageNumber);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public ProductsListAdapter getAdapter() {
        return productsListAdapter;
    }

    public void loadMoreProducts() {
        fetchProductsList(pageNumber++);
    }

    public void onProductClicked(ProductViewModel productViewModel) {
        transientDataProvider.saveUseCase(new ProductUseCase(productViewModel.getProduct()));
        launchProductDetailsLiveData.setValue(0);
    }

    void fetchProductsList(int pageNumber) {
        shouldShowProgressBar.set(true);
        compositeDisposable.add(walmartLabsProvider.getProductsList(pageNumber, PAGE_SIZE)
        .subscribe(this::populateData, throwable -> shouldShowProgressBar.set(false)));
    }

    private void populateData(ProductsList productsList) {
        List<Product> products = productsList.getProducts();
        for (Product product : products) {
            ProductViewModel productViewModel = new ProductViewModel();
            productViewModel.productName.set(product.getProductName());
            productViewModel.productPrice.set(product.getPrice());
            productViewModel.setProduct(product);
            productViewModels.add(productViewModel);
        }
        shouldShowProgressBar.set(false);
        productsListAdapter.notifyDataSetChanged();
    }
}
