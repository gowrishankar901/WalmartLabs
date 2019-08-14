package com.walmart.walmartlabstest;

import androidx.recyclerview.widget.RecyclerView;

import com.walmart.walmartlabstest.databinding.ItemProductViewHolderBinding;
import com.walmart.walmartlabstest.viewmodel.ProductViewModel;
import com.walmart.walmartlabstest.viewmodel.ProductsListViewModel;

public class ProductItemViewHolder extends RecyclerView.ViewHolder {

    private ItemProductViewHolderBinding productViewHolderBinding;

    public ProductItemViewHolder(ItemProductViewHolderBinding productViewHolderBinding) {
        super(productViewHolderBinding.getRoot());
        this.productViewHolderBinding = productViewHolderBinding;
    }

    public void bind(ProductsListViewModel productsListViewModel, ProductViewModel productViewModel) {
        productViewHolderBinding.setListViewModel(productsListViewModel);
        productViewHolderBinding.setViewModel(productViewModel);
        productViewHolderBinding.executePendingBindings();
    }
}
