package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.databinding.FragmentErrorPickingProductBinding
import com.leetotheyutothelee.adminnotificationapp.extension.repeatOnStarted
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.adapter.ErrorPickingProductAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.viewmodel.ErrorPickingProductViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val CATEGORY_TYPE = "categoryType"

@AndroidEntryPoint
class ErrorPickingProductFragment : Fragment() {

    private lateinit var binding: FragmentErrorPickingProductBinding
    private val viewModel: ErrorPickingProductViewModel by viewModels()

    private val errorPickingProductAdapter: ErrorPickingProductAdapter by lazy {
        ErrorPickingProductAdapter()
    }

    private var categoryType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_error_picking_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(CATEGORY_TYPE) }?.apply {
            getString(CATEGORY_TYPE)?.let {
                categoryType = it
            }
        }

        with(binding) {
            with(rvErrorPickingProducts) {
                adapter = errorPickingProductAdapter
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
                val errorPickingProducts = event.errorPickingProductsModel.errorPickingProducts?.filter {
                    categoryType == it.categoryType
                }
                println("ErrorPickingProductFragment: handleEvent: errorPickingProducts: ${errorPickingProducts}")
                errorPickingProductAdapter.submitList(errorPickingProducts)
            }
        }
    }

    companion object {
        fun newInstance(categoryType: String) = ErrorPickingProductFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY_TYPE, categoryType)
            }
        }
    }
}