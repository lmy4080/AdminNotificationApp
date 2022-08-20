package com.leetotheyutothelee.adminnotificationapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leetotheyutothelee.adminnotificationapp.base.MutableEventFlow
import com.leetotheyutothelee.adminnotificationapp.base.asEventFlow
import com.leetotheyutothelee.adminnotificationapp.domain.usecase.SectorListUseCase
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectorListViewModel @Inject constructor(
    private val sectorListUseCase: SectorListUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getSectorList() {
        viewModelScope.launch {
            sectorListUseCase.execute(
                params = null,
                onSuccess = {
                    event(Event.SectorList(it))
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
        data class SectorList(val sectorListModel: SectorListModel): Event()
    }
}