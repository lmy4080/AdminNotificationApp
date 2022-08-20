package com.leetotheyutothelee.adminnotificationapp.data.remote.response

data class SectorListResponse(
    val sectorList: ArrayList<SectorResponse>?
)

data class SectorResponse(
    val sectorType: String?,
    val sectorName: String?
)
