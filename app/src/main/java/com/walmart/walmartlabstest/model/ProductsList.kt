package com.walmart.walmartlabstest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ProductsList {
    @SerializedName("products")
    @Expose
    var products:List<Product>? = null
    @SerializedName("totalProducts")
    @Expose
    var totalProducts:Int? = null
    @SerializedName("pageNumber")
    @Expose
    var pageNumber:Int? = null
    @SerializedName("pageSize")
    @Expose
    var pageSize:Int? = null
    @SerializedName("statusCode")
    @Expose
    var statusCode:Int? = null
}
