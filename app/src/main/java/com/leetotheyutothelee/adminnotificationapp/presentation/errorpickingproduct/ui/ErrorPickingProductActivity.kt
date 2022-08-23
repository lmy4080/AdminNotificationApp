package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.tabs.TabLayoutMediator
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.constant.BroadcastConstant
import com.leetotheyutothelee.adminnotificationapp.databinding.ActivityErrorPickingProductBinding
import com.leetotheyutothelee.adminnotificationapp.extension.repeatOnStarted
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.adapter.ErrorPickingProductPagerAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.viewmodel.ErrorPickingProductViewModel
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorPickingProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorPickingProductBinding
    private val viewModel: ErrorPickingProductViewModel by viewModels()

    private val mMessageReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val errorPickingProduct = intent?.getSerializableExtra("errorPickingProduct") as? ErrorPickingProductModel
            errorPickingProduct?.let {
                viewModel.addErrorPickingProduct(it)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager
            .getInstance(this)
            .unregisterReceiver(mMessageReceiver)
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter(BroadcastConstant.EVENT_NOTIFICATION))

        viewModel.getErrorPickingProducts()
    }

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
        }
    }

    private fun handleEvent(event: ErrorPickingProductViewModel.Event) {
        when(event) {
            is ErrorPickingProductViewModel.Event.GetErrorPickingProducts -> {
                with(viewModel) {
                    getCategories()
                }
            }
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
        }
    }
}