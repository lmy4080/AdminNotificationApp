package com.leetotheyutothelee.adminnotificationapp.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.databinding.ActivitySectorListBinding
import com.leetotheyutothelee.adminnotificationapp.extension.repeatOnStarted
import com.leetotheyutothelee.adminnotificationapp.presentation.adapter.SectorAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.viewmodel.SectorListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectorListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySectorListBinding
    private val viewModel: SectorListViewModel by viewModels()
    private val sectorAdapter: SectorAdapter by lazy {
        SectorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sector_list)

        with(binding) {

            with(rvSectorList) {
                adapter = sectorAdapter
            }
        }

        with(viewModel) {
            repeatOnStarted {
                eventFlow.collect { event -> handleEvent(event) }
            }
        }
    }

    private fun handleEvent(event: SectorListViewModel.Event) {
        when(event) {
            is SectorListViewModel.Event.SectorList -> {
                with(binding) {
                    sectorAdapter.submitList(event.sectorListModel.sectorList)
                }
            }
        }
    }
}