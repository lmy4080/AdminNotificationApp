package com.leetotheyutothelee.adminnotificationapp.domain.usecase

import com.leetotheyutothelee.adminnotificationapp.base.SingleUseCase
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepository
import com.leetotheyutothelee.adminnotificationapp.mapper.toPresentation
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorsModel
import javax.inject.Inject

class SectorsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
): SingleUseCase<SectorsModel, Any>() {

    override suspend fun buildUseCaseSingle(params: Any?): SectorsModel {
        return apiRepository.getSectors().toPresentation()
    }
}