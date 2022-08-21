package com.leetotheyutothelee.adminnotificationapp.data.remote.response

data class ErrorPickingProductsResponse(
    val errorPickingProducts: ArrayList<ErrorPickingProductResponse>?
)

data class ErrorPickingProductResponse(
    val categoryType: String?,
    val categoryName: String?,
    val productName: String?,
    val errorTime: String?,
    val requestBoxNo: Int?,
    val expectedCount: Int?,
    val actualCount: Int?,
)
