package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.data.source.ApiDataSource
import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val dataSource: ApiDataSource
): ApiRepository {

    override suspend fun getSectors(): Sectors {
        return dataSource.getSectors()
    }

    override suspend fun getCategories(): Categories {
        return dataSource.getCategories()
    }
}