package com.walmart.walmartlabstest.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.walmart.walmartlabstest.model.Product;
import com.walmart.walmartlabstest.serviceprovider.GlideProvider;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Product.class, ProductUseCase.class})
public class ProductDetailsViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private TransientDataProvider transientDataProvider;

    @Mock
    private GlideProvider glideProvider;

    private Product product = PowerMockito.mock(Product.class);
    private ProductUseCase useCase = PowerMockito.mock(ProductUseCase.class);
    private ProductDetailsViewModel subject;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        subject = new ProductDetailsViewModel(glideProvider, transientDataProvider);
    }

    @Test
    public void onPageLoad_productDetailsUseCaseDoesNotExist_doesNotPopulateData() {
        assertNull(subject.productName.get());
        assertNull(subject.imageUrl.get());
        assertNull(subject.description.get());
        assertNull(subject.reviewCount.get());
        assertNull(subject.rating.get());
        assertNull(subject.productAvailability.get());
    }

    @Test
    public void onPageLoad_productDetailsUseCaseExists_populatesData() {
        when(product.getProductName()).thenReturn("productName");
        when(product.getProductImage()).thenReturn("productImage");
        when(product.getLongDescription()).thenReturn("description");
        when(product.getReviewCount()).thenReturn(5);
        when(product.getReviewRating()).thenReturn(2.5f);
        when(product.getInStock()).thenReturn(true);
        when(useCase.getProduct()).thenReturn(product);
        when(transientDataProvider.containsUseCase(ProductUseCase.class)).thenReturn(true);
        when(transientDataProvider.getUseCase(ProductUseCase.class)).thenReturn(useCase);

        subject = new ProductDetailsViewModel(glideProvider, transientDataProvider);

        assertThat(subject.productName.get()).isEqualTo("productName");
        assertThat(subject.imageUrl.get()).isEqualTo("productImage");
        assertThat(subject.description.get()).isEqualTo("description");
        assertThat(subject.reviewCount.get()).isEqualTo("5");
        assertThat(subject.rating.get()).isEqualTo("2.5");
        assertThat(subject.productAvailability.get()).isEqualTo("Available");
    }

    @Test
    public void onPageLoad_productDetailsUseCaseExistsButSomeValuesAreNull_populatesOnlyDataThatAreAvailable() {
        when(product.getProductName()).thenReturn("productName");
        when(product.getLongDescription()).thenReturn("description");
        when(product.getReviewCount()).thenReturn(5);
        when(product.getReviewRating()).thenReturn(2.5f);
        when(useCase.getProduct()).thenReturn(product);
        when(transientDataProvider.containsUseCase(ProductUseCase.class)).thenReturn(true);
        when(transientDataProvider.getUseCase(ProductUseCase.class)).thenReturn(useCase);

        subject = new ProductDetailsViewModel(glideProvider, transientDataProvider);

        assertThat(subject.productName.get()).isEqualTo("productName");
        assertThat(subject.imageUrl.get()).isEqualTo("");
        assertThat(subject.description.get()).isEqualTo("description");
        assertThat(subject.reviewCount.get()).isEqualTo("5");
        assertThat(subject.rating.get()).isEqualTo("2.5");
        assertThat(subject.productAvailability.get()).isEqualTo("Not Available");
    }
}