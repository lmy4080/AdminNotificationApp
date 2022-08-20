package com.leetotheyutothelee.adminnotificationapp.domain.usecase

import com.leetotheyutothelee.adminnotificationapp.base.SingleUseCase
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepository
import com.leetotheyutothelee.adminnotificationapp.mapper.toPresentation
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorListModel
import javax.inject.Inject

class SectorListUseCase @Inject constructor(
    private val apiRepository: ApiRepository
): SingleUseCase<SectorListModel, Any>() {

    override suspend fun buildUseCaseSingle(params: Any?): SectorListModel {
        return apiRepository.getSectorList().toPresentation()
    }
}