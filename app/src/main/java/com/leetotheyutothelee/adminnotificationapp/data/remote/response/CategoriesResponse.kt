package com.leetotheyutothelee.adminnotificationapp.data.remote.response

data class CategoriesResponse(
    val categories: ArrayList<CategoryResponse>?
)

data class CategoryResponse(
    val categoryType: String?,
    val categoryName: String?
)
