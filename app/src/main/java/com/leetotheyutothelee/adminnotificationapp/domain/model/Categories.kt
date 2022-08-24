package com.leetotheyutothelee.adminnotificationapp.domain.model

data class Categories(
    val categories: ArrayList<Category>?
)

data class Category(
    val categoryType: String?,
    val categoryName: String?
)
