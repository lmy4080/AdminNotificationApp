package com.leetotheyutothelee.adminnotificationapp.data.repository

import com.leetotheyutothelee.adminnotificationapp.domain.model.SectorList

interface ApiRepository {
    suspend fun getSectorList(): SectorList
}