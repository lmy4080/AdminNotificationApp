package com.leetotheyutothelee.adminnotificationapp.presentation.model

data class SectorsModel(
    val sectorList: ArrayList<SectorModel>?
)

data class SectorModel(
    var sectorType: String?,
    var sectorName: String?
)