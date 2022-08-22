package com.leetotheyutothelee.adminnotificationapp.domain.model

data class Sectors(
    val sectorList: ArrayList<Sector>?
)

data class Sector(
    val sectorType: String?,
    val sectorName: String?
)
