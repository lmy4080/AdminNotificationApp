package com.leetotheyutothelee.adminnotificationapp.data.remote.response

data class SectorsResponse(
    val sectors: ArrayList<SectorResponse>?
)

data class SectorResponse(
    val sectorType: String?,
    val sectorName: String?
)
