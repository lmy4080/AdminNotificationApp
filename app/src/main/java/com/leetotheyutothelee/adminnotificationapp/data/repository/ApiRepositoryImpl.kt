package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.data.source.ApiDataSource
import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.ErrorPickingProducts
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val dataSource: ApiDataSource
): ApiRepository {

    override suspend fun getSectorList(): Sectors {
        return dataSource.getSectorList()
    }

    override suspend fun getCategoryList(): Categories {
        return dataSource.getCategoryList()
    }

    override suspend fun getErrorPickingProducts(): ErrorPickingProducts {
        return dataSource.getErrorPickingProducts()
    }
}