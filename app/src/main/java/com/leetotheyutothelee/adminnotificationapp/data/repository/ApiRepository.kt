package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.ErrorPickingProducts
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors

interface ApiRepository {
    suspend fun getSectorList(): Sectors
    suspend fun getCategoryList(): Categories
    suspend fun getErrorPickingProducts(): ErrorPickingProducts
}