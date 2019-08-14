package com.walmart.walmartlabstest.viewmodel;

import androidx.databinding.ObservableField;

import com.walmart.walmartlabstest.model.Product;

import javax.inject.Inject;

public class ProductViewModel {
    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<String> productPrice = new ObservableField<>();

    private Product product;

    @Inject
    public ProductViewModel() { }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
