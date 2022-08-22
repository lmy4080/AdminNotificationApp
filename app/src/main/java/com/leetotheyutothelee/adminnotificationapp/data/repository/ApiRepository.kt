package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.domain.model.Categories
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sectors

interface ApiRepository {
    suspend fun getSectors(): Sectors
    suspend fun getCategories(): Categories
}