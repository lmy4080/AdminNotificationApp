package com.leetotheyutothelee.adminnotificationapp.presentation.model

import java.io.Serializable

data class ErrorPickingProductsModel(
    val errorPickingProducts: ArrayList<ErrorPickingProductModel>?
): Serializable

data class ErrorPickingProductModel(
    val categoryType: String?,
    val categoryName: String?,
    val productName: String?,
    var errorTime: String?,
    val requestBoxNo: String?,
    val expectedCount: Int?,
    val actualCount: Int?,
    var isOver: Boolean?,
    var displayCount: Int?
): Serializable
