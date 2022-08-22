package com.leetotheyutothelee.adminnotificationapp.mapper

import com.leetotheyutothelee.adminnotificationapp.data.remote.response.CategoriesResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.CategoryResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorsResponse
import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.Category
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sector
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoriesModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoryModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorsModel

fun SectorsResponse.toDomain() = Sectors(
    sectorList = sectors?.map { it.toDomain() } as? ArrayList<Sector>
)

fun Sectors.toPresentation() = SectorsModel(
    sectorList = sectorList?.map { it.toPresentation() } as? ArrayList<SectorModel>
)

fun SectorResponse.toDomain() = Sector(
    sectorType = sectorType,
    sectorName = sectorName
)

fun Sector.toPresentation() = SectorModel(
    sectorType = sectorType,
    sectorName = sectorName
)

fun CategoriesResponse.toDomain() = Categories(
    categories = categories?.map { it.toDomain() } as? ArrayList<Category>
)

fun Categories.toPresentation() = CategoriesModel(
    categories = categories?.map { it.toPresentation() } as? ArrayList<CategoryModel>
)

fun CategoryResponse.toDomain() = Category(
    categoryType = categoryType,
    categoryName = categoryName
)

fun Category.toPresentation() = CategoryModel(
    categoryType = categoryType,
    categoryName = categoryName
)