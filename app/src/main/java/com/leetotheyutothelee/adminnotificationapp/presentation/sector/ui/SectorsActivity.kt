package com.leetotheyutothelee.adminnotificationapp.presentation.sector.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.databinding.ActivitySectorsBinding
import com.leetotheyutothelee.adminnotificationapp.extension.repeatOnStarted
import com.leetotheyutothelee.adminnotificationapp.presentation.sector.adapter.SectorAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.sector.viewmodel.SectorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySectorsBinding
    private val viewModel: SectorsViewModel by viewModels()
    private val sectorAdapter: SectorAdapter by lazy {
        SectorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sectors)

        with(binding) {

            with(rvSectorList) {
                adapter = sectorAdapter
            }
        }

        with(viewModel) {
            repeatOnStarted {
                eventFlow.collect { event -> handleEvent(event) }
            }
            getSectors()
        }
    }

    private fun handleEvent(event: SectorsViewModel.Event) {
        when(event) {
            is SectorsViewModel.Event.Sectors -> {
                with(binding) {
                    sectorAdapter.submitList(event.sectorListModel.sectorList)
                }
            }
        }
    }
}