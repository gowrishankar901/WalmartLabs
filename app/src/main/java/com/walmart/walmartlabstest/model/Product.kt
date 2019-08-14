package com.walmart.walmartlabstest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Product {
    @SerializedName("productId")
    @Expose
    var productId:String? = null
    @SerializedName("productName")
    @Expose
    var productName:String? = null
    @SerializedName("shortDescription")
    @Expose
    var shortDescription:String? = null
    @SerializedName("longDescription")
    @Expose
    var longDescription:String? = null
    @SerializedName("price")
    @Expose
    var price:String? = null
    @SerializedName("productImage")
    @Expose
    var productImage:String? = null
    @SerializedName("reviewRating")
    @Expose
    var reviewRating:Float? = null
    @SerializedName("reviewCount")
    @Expose
    var reviewCount:Int? = null
    @SerializedName("inStock")
    @Expose
    var inStock:Boolean? = null
}
