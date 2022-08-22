package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.data.remote.ApiService
import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors
import com.leetotheyutothelee.adminnotificationapp.mapper.toDomain
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): ApiDataSource {

    override suspend fun getSectors(): Sectors {
        return apiService.getSectors().toDomain()
    }

    override suspend fun getCategories(): Categories {
        return apiService.getCategories().toDomain()
    }
}