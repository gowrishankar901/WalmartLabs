package com.walmart.walmartlabstest;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.walmart.walmartlabstest.databinding.ItemProductViewHolderBinding;
import com.walmart.walmartlabstest.viewmodel.ProductViewModel;
import com.walmart.walmartlabstest.viewmodel.ProductsListViewModel;

import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductItemViewHolder> {

    private List<ProductViewModel> productViewModels;
    private ProductsListViewModel productsListViewModel;

    public ProductsListAdapter(List<ProductViewModel> productViewModels, ProductsListViewModel productsListViewModel) {
        this.productsListViewModel = productsListViewModel;
        this.productViewModels = productViewModels;
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductViewHolderBinding itemProductViewHolderBinding = ItemProductViewHolderBinding.inflate(layoutInflater, parent, false);
        return new ProductItemViewHolder(itemProductViewHolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {
        ProductViewModel productViewModel = productViewModels.get(position);
        holder.bind(productsListViewModel, productViewModel);
    }

    @Override
    public int getItemCount() {
        return productViewModels != null ? productViewModels.size() : 0;
    }
}
