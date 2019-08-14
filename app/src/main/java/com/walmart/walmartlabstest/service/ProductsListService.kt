package com.walmart.walmartlabstest.service

import com.walmart.walmartlabstest.model.ProductsList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsListService {

    @GET("walmartproducts/{pageNumber}/{pageSize}")
    fun getProductsList(@Path("pageNumber") pageNumber:Int, @Path("pageSize") pageSize:Int): Single<ProductsList>
}