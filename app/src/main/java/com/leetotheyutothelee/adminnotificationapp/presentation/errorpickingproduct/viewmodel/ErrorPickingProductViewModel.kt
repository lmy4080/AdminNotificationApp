package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leetotheyutothelee.adminnotificationapp.base.MutableEventFlow
import com.leetotheyutothelee.adminnotificationapp.base.asEventFlow
import com.leetotheyutothelee.adminnotificationapp.constant.ViewConstant.CATEGORY_NAME_ALL
import com.leetotheyutothelee.adminnotificationapp.constant.ViewConstant.CATEGORY_TYPE_ALL
import com.leetotheyutothelee.adminnotificationapp.domain.usecase.CategoriesUseCase
import com.leetotheyutothelee.adminnotificationapp.domain.usecase.ErrorPickingProductsUseCase
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoriesModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoryModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class ErrorPickingProductViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase,
    private val errorPickingProductsUseCase: ErrorPickingProductsUseCase
): ViewModel() {

    private var _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private var _errorPickingProducts: MutableLiveData<ErrorPickingProductsModel> = MutableLiveData()
    val errorPickingProducts: LiveData<ErrorPickingProductsModel> get() = _errorPickingProducts

    fun getCategories() {
        viewModelScope.launch {
            categoriesUseCase.execute(
                params = null,
                onSuccess = {
                    // 화면 비즈니스
                    // 전체 카테고리 추가
                    it.categories?.add(
                        0,
                        CategoryModel(
                            categoryType = CATEGORY_TYPE_ALL,
                            categoryName = CATEGORY_NAME_ALL
                        )
                    )

                    event(Event.GetCategories(it))
                },
                onError = {

                }
            )
        }
    }

    fun getErrorPickingProducts() {
        viewModelScope.launch {
            errorPickingProductsUseCase.execute(
                params = null,
                onSuccess = {
                    // 화면 비즈니스
                    it.errorPickingProducts?.forEach { errorPickingProduct ->
                        with(errorPickingProduct) {
                            // 수량 초과 여부
                            isOver = (expectedCount ?: 0) < (actualCount ?: 0)
                            // 화면에 보여지는 수량
                            displayCount = abs((expectedCount ?: 0) - (actualCount ?: 0))
                        }
                    }

                    _errorPickingProducts.value = it
                    event(Event.GetErrorPickingProducts(it))
                },
                onError = {

                }
            )
        }
    }

    fun addErrorPickingProduct(errorPickingProductModel: ErrorPickingProductModel) {
        _errorPickingProducts.value = _errorPickingProducts.value?.apply {
            errorPickingProducts?.add(0, errorPickingProductModel)
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class GetCategories(val categoriesModel: CategoriesModel): Event()
        data class GetErrorPickingProducts(val errorPickingProductsModel: ErrorPickingProductsModel): Event()
    }
}