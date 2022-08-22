package com.leetotheyutothelee.adminnotificationapp.presentation.sector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leetotheyutothelee.adminnotificationapp.base.MutableEventFlow
import com.leetotheyutothelee.adminnotificationapp.base.asEventFlow
import com.leetotheyutothelee.adminnotificationapp.domain.usecase.SectorsUseCase
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectorsViewModel @Inject constructor(
    private val sectorListUseCase: SectorsUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getSectors() {
        viewModelScope.launch {
            sectorListUseCase.execute(
                params = null,
                onSuccess = {
                    event(Event.Sectors(it))
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
        data class Sectors(val sectorListModel: SectorsModel): Event()
    }
}