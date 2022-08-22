package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leetotheyutothelee.adminnotificationapp.base.MutableEventFlow
import com.leetotheyutothelee.adminnotificationapp.base.asEventFlow
import com.leetotheyutothelee.adminnotificationapp.domain.usecase.CategoriesUseCase
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoriesModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ErrorPickingProductViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
): ViewModel() {

    private var _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    //private var _errorPickingProductsModel: ErrorPickingProductsModel? = null
    private var _errorPickingProductsModel: ErrorPickingProductsModel? = ErrorPickingProductsModel(
        errorPickingProducts = arrayListOf(
            ErrorPickingProductModel(
                categoryType = "음료",
                categoryName = "생수/음료/우유/커피",
                productName = "",
                errorTime = "",
                requestBoxNo = 0,
                expectedCount = 0,
                actualCount = 0,
                isOver = false,
                2
            ),
            ErrorPickingProductModel(
                categoryType = "건강식품",
                categoryName = "건강식품",
                productName = "",
                errorTime = "",
                requestBoxNo = 0,
                expectedCount = 0,
                actualCount = 0,
                isOver = true,
                2
            ),
            ErrorPickingProductModel(
                categoryType = "취미용품",
                categoryName = "취미용품",
                productName = "",
                errorTime = "",
                requestBoxNo = 0,
                expectedCount = 0,
                actualCount = 0,
                isOver = false,
                1
            ),
            ErrorPickingProductModel(
                categoryType = "주방용품",
                categoryName = "주방용품",
                productName = "",
                errorTime = "",
                requestBoxNo = 0,
                expectedCount = 0,
                actualCount = 0,
                isOver = true,
                20
            ),
        )
    )

    fun getCategories() {
        viewModelScope.launch {
            categoriesUseCase.execute(
                params = null,
                onSuccess = {
                    event(Event.GetCategories(it))
                },
                onError = {

                }
            )
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