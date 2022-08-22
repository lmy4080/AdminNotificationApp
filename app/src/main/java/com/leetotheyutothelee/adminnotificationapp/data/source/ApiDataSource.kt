package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors

interface ApiDataSource {
    suspend fun getSectors(): Sectors
    suspend fun getCategories(): Categories
}