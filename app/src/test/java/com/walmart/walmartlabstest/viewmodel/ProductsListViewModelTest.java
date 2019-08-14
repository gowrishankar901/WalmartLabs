package com.walmart.walmartlabstest.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.walmart.walmartlabstest.model.Product;
import com.walmart.walmartlabstest.model.ProductsList;
import com.walmart.walmartlabstest.serviceprovider.WalmartLabsProvider;
import com.walmart.walmartlabstest.shared.ProductUseCase;
import com.walmart.walmartlabstest.shared.TransientDataProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;

import io.reactivex.Single;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProductsList.class, Product.class})
public class ProductsListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private WalmartLabsProvider walmartLabsProvider;

    @Mock
    private TransientDataProvider transientDataProvider;

    private ProductsListViewModel subject;
    private ProductsList productsList = PowerMockito.mock(ProductsList.class);
    private Product product = PowerMockito.mock(Product.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(walmartLabsProvider.getProductsList(anyInt(), anyInt())).thenReturn(Single.error(new Throwable()));

        subject = new ProductsListViewModel(walmartLabsProvider, transientDataProvider);
    }

    @Test
    public void onPageLoad_fetchesProductsList() {
        verify(walmartLabsProvider).getProductsList(1, 20);
    }

    @Test
    public void onCleared_disposesSubscription() {
        assertNotNull(subject.compositeDisposable);

        subject.onCleared();

        assertTrue(subject.compositeDisposable.isDisposed());
    }

    @Test
    public void fetchProductsList_onSuccess_populatesList() {
        when(product.getProductName()).thenReturn("productName");
        when(product.getPrice()).thenReturn("price");
        when(productsList.getProducts()).thenReturn(Collections.singletonList(product));
        when(walmartLabsProvider.getProductsList(anyInt(), anyInt())).thenReturn(Single.just(productsList));
        subject = new ProductsListViewModel(walmartLabsProvider, transientDataProvider);

        assertFalse(subject.shouldShowProgressBar.get());
        assertThat(subject.productViewModels.size()).isEqualTo(1);
    }

    @Test
    public void fetchProductsList_onFailure_doesNotPopulateList() {
        when(walmartLabsProvider.getProductsList(anyInt(), anyInt())).thenReturn(Single.error(new Throwable()));

        subject.fetchProductsList(1);

        assertFalse(subject.shouldShowProgressBar.get());
        assertThat(subject.productViewModels.size()).isEqualTo(0);
    }

    @Test
    public void onProductClicked_savesProductUseCase() {
        ProductViewModel productViewModel = mock(ProductViewModel.class);
        Product product = mock(Product.class);
        when(productViewModel.getProduct()).thenReturn(product);

        subject.onProductClicked(productViewModel);

        verify(transientDataProvider).saveUseCase(new ProductUseCase(product));
        assertThat(subject.launchProductDetailsLiveData.getValue()).isEqualTo(0);
    }
}