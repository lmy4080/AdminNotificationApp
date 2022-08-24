package com.leetotheyutothelee.adminnotificationapp.domain.model

data class ErrorPickingProducts(
    val errorPickingProducts: ArrayList<ErrorPickingProduct>?
)

data class ErrorPickingProduct(
    val categoryType: String?,
    val categoryName: String?,
    val productName: String?,
    val errorTime: String?,
    val requestBoxNo: String?,
    val expectedCount: Int?,
    val actualCount: Int?,
    var isOver: Boolean?,
    var displayCount: Int?
)
