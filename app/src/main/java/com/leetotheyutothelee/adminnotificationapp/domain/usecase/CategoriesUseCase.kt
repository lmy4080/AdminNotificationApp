package com.leetotheyutothelee.adminnotificationapp.domain.usecase

import com.leetotheyutothelee.adminnotificationapp.base.SingleUseCase
import com.leetotheyutothelee.adminnotificationapp.data.repository.ApiRepository
import com.leetotheyutothelee.adminnotificationapp.mapper.toPresentation
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoriesModel
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val apiRepository: ApiRepository
): SingleUseCase<CategoriesModel, Any>() {

    override suspend fun buildUseCaseSingle(params: Any?): CategoriesModel {
        return apiRepository.getCategoryList().toPresentation()
    }
}