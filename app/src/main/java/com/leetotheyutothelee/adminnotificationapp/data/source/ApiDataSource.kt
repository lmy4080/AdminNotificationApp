package com.leetotheyutothelee.adminnotificationapp.data.source

import com.leetotheyutothelee.adminnotificationapp.domain.model.SectorList

interface ApiDataSource {
    suspend fun getSectorList(): SectorList
}