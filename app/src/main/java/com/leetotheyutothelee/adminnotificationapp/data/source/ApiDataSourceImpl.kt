package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.data.remote.ApiService
import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.ErrorPickingProducts
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors
import com.leetotheyutothelee.adminnotificationapp.mapper.toDomain
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(
    private val apiService: ApiService
): ApiDataSource {

    override suspend fun getSectorList(): Sectors {
        return apiService.getSectorList().toDomain()
    }

    override suspend fun getCategoryList(): Categories {
        return apiService.getCategoryList().toDomain()
    }

    override suspend fun getErrorPickingProducts(): ErrorPickingProducts {
        return apiService.getErrorPickingProducts().toDomain()
    }
}