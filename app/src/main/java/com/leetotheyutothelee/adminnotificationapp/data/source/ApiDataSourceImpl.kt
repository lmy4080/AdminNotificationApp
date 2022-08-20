package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.data.remote.ApiService
import com.leetotheyutothelee.adminnotificationapp.domain.model.SectorList
import com.leetotheyutothelee.adminnotificationapp.mapper.toDomain
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): ApiDataSource {

    override suspend fun getSectorList(): SectorList {
        return apiService.getSectorList().toDomain()
    }
}