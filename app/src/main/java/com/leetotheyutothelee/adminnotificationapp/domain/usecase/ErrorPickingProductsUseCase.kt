package com.leetotheyutothelee.adminnotificationapp.domain.usecase

import com.leetotheyutothelee.adminnotificationapp.base.SingleUseCase
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepository
import com.leetotheyutothelee.adminnotificationapp.mapper.toPresentation
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductsModel
import javax.inject.Inject

class ErrorPickingProductsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
): SingleUseCase<ErrorPickingProductsModel, Any>() {

    override suspend fun buildUseCaseSingle(params: Any?): ErrorPickingProductsModel {
        return apiRepository.getErrorPickingProducts().toPresentation()
    }
}