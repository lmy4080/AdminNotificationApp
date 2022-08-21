package com.leetotheyutothelee.adminnotificationapp.presentation.model

data class ErrorPickingProductsModel(
    val errorPickingProducts: ArrayList<ErrorPickingProductModel>?
)

data class ErrorPickingProductModel(
    val categoryType: String?,
    val categoryName: String?,
    val productName: String?,
    val errorTime: String?,
    val requestBoxNo: Int?,
    val expectedCount: Int?,
    val actualCount: Int?,
)
