package com.leetotheyutothelee.adminnotificationapp.mapper

import com.leetotheyutothelee.adminnotificationapp.data.remote.response.*
import com.leetotheyutothelee.adminnotificationapp.domain.model.*
import com.leetotheyutothelee.adminnotificationapp.presentation.model.*

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

fun ErrorPickingProductsResponse.toDomain() = ErrorPickingProducts(
    errorPickingProducts = errorPickingProducts?.map {
        it.toDomain()
    } as? ArrayList<ErrorPickingProduct>
)

fun ErrorPickingProducts.toPresentation() = ErrorPickingProductsModel(
    errorPickingProducts = errorPickingProducts?.map {
        it.toPresentation()
    } as? ArrayList<ErrorPickingProductModel>
)

fun ErrorPickingProductResponse.toDomain() = ErrorPickingProduct(
    categoryType = categoryType,
    categoryName = categoryName,
    productName = productName,
    errorTime = errorTime,
    requestBoxNo = requestBoxNo,
    expectedCount = expectedCount,
    actualCount = actualCount,
    isOver = isOver,
    displayCount = displayCount
)

fun ErrorPickingProduct.toPresentation() = ErrorPickingProductModel(
    categoryType = categoryType,
    categoryName = categoryName,
    productName = productName,
    errorTime = errorTime,
    requestBoxNo = requestBoxNo,
    expectedCount = expectedCount,
    actualCount = actualCount,
    isOver = isOver,
    displayCount = displayCount
)