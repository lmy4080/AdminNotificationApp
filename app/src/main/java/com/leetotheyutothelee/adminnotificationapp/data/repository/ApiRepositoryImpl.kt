package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.data.source.ApiDataSource
import com.leetotheyutothelee.adminnotificationapp.domain.model.SectorList
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val dataSource: ApiDataSource
): ApiRepository {

    override suspend fun getSectorList(): SectorList {
        return dataSource.getSectorList()
    }
}