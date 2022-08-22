package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.databinding.ActivityErrorPickingProductBinding
import com.leetotheyutothelee.adminnotificationapp.extension.repeatOnStarted
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.adapter.ErrorPickingProductPagerAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.viewmodel.ErrorPickingProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorPickingProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorPickingProductBinding
    private val viewModel: ErrorPickingProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_error_picking_product)

        with(binding) {

            with(viewMode) {
                isAdmin = true
                setOnClickListener {
                    isAdmin = !(isAdmin ?: false)
                }
            }
        }

        with(viewModel) {
            repeatOnStarted {
                eventFlow.collect { event -> handleEvent(event) }
            }
            getCategories()
        }
    }

    private fun handleEvent(event: ErrorPickingProductViewModel.Event) {
        when(event) {
            is ErrorPickingProductViewModel.Event.GetCategories -> {
                with(binding) {

                    // 상단 카테고리 탭, 뷰 페이저 설정
                    event.categoriesModel.categories?.let {
                        with(viewPager) {
                            adapter = ErrorPickingProductPagerAdapter(this@ErrorPickingProductActivity, it)
                        }
                        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                            tab.text = it[position].categoryName ?: ""
                        }.attach()
                    }
                }
            }
            else -> { }
        }
    }
}