package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.ErrorPickingProducts
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors

interface ApiDataSource {
    suspend fun getSectorList(): Sectors
    suspend fun getCategoryList(): Categories
    suspend fun getErrorPickingProducts(): ErrorPickingProducts
}