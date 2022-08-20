package com.leetotheyutothelee.adminnotificationapp.mapper

import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorListResponse
import com.leetotheyutothelee.adminnotificationapp.data.remote.response.SectorResponse
import com.leetotheyutothelee.adminnotificationapp.domain.model.Sector
import com.leetotheyutothelee.adminnotificationapp.domain.model.SectorList
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorListModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorModel

fun SectorListResponse.toDomain() = SectorList(
    sectorList = sectorList?.map { it.toDomain() } as? ArrayList<Sector>
)

fun SectorList.toPresentation() = SectorListModel(
    sectorList = sectorList?.map { it.toPresentation() } as? ArrayList<SectorModel>
)

fun SectorResponse.toDomain() = Sector(
    sectorType = sectorType,
    sectorName = sectorName
)

fun Sector.toPresentation() = SectorModel(
    sectorType = sectorType,
    sectorName = sectorName
)