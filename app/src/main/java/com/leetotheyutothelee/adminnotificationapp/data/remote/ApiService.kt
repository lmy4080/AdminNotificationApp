package com.leetotheyutothelee.adminnotificationapp.data.remote

import com.leetotheyutothelee.adminnotificationapp.data.remote.response.CategoriesResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.ErrorPickingProductsResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/sectors")
    suspend fun getSectorList(): SectorsResponse

    @GET("/categories")
    suspend fun getCategoryList(): CategoriesResponse

    @GET("/errorPickingProducts")
    suspend fun getErrorPickingProducts(): ErrorPickingProductsResponse
}