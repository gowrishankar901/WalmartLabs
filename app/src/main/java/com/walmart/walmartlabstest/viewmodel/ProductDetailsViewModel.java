package com.walmart.walmartlabstest.viewmodel;

import android.text.Html;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.walmart.walmartlabstest.model.Product;
import com.walmart.walmartlabstest.serviceprovider.GlideProvider;
import com.walmart.walmartlabstest.shared.ProductUseCase;
import com.walmart.walmartlabstest.shared.TransientDataProvider;

import javax.inject.Inject;

import static android.text.Html.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH;

public class ProductDetailsViewModel extends ViewModel {

    private static final String IMAGE_URL_HOST = "https://mobile-tha-server.firebaseapp.com";

    public ObservableField<String> productName = new ObservableField<>();
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> rating = new ObservableField<>();
    public ObservableField<String> reviewCount = new ObservableField<>();
    public ObservableField<String> productAvailability = new ObservableField<>();

    private final GlideProvider glideProvider;
    private final TransientDataProvider transientDataProvider;

    @Inject
    public ProductDetailsViewModel(GlideProvider glideProvider, TransientDataProvider transientDataProvider) {
        this.glideProvider = glideProvider;
        this.transientDataProvider = transientDataProvider;
        populateData();
    }

    public GlideProvider getGlideProvider() {
        return glideProvider;
    }

    private void populateData() {
        if (transientDataProvider.containsUseCase(ProductUseCase.class)) {
            Product product = transientDataProvider.getUseCase(ProductUseCase.class).getProduct();
            String name = product.getProductName() != null ? getHtmlFormattedString(product.getProductName()) : "";
            String url = product.getProductImage() != null ? IMAGE_URL_HOST + product.getProductImage() : "";
            boolean isProductAvailable = product.getInStock() != null ? product.getInStock() : false;
            String productDescription = product.getLongDescription() != null ? getHtmlFormattedString(product.getLongDescription()) : "";
            productName.set(name);
            imageUrl.set(url);
            description.set(productDescription);
            rating.set(product.getReviewRating() != null ? String.valueOf(product.getReviewRating()) : "");
            reviewCount.set(product.getReviewCount() != null ? String.valueOf(product.getReviewCount()) : "");
            productAvailability.set(isProductAvailable ? " Available" : " Not Available");
        }
    }

    private String getHtmlFormattedString(String description) {
        return Html.fromHtml(description, FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH).toString();
    }
}
