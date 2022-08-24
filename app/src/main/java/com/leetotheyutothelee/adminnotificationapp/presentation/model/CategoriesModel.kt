package com.leetotheyutothelee.adminnotificationapp.presentation.model

data class CategoriesModel(
    val categories: ArrayList<CategoryModel>?
)

data class CategoryModel(
    val categoryType: String?,
    val categoryName: String?
)
