package com.leetotheyutothelee.adminnotificationapp.data.remote

import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/sectors")
    suspend fun getSectorList(): SectorListResponse
}