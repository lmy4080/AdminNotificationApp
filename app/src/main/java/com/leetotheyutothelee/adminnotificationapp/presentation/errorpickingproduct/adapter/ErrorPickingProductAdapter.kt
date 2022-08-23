package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.base.BaseViewHolder
import com.leetotheyutothelee.adminnotificationapp.databinding.ItemErrorPickingProductBinding
import com.leetotheyutothelee.adminnotificationapp.extension.toDate
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductModel

class ErrorPickingProductAdapter: ListAdapter<ErrorPickingProductModel, BaseViewHolder>(ERROR_PICKING_PRODUCT_DIFF) {

    companion object{
        private val ERROR_PICKING_PRODUCT_DIFF = object: DiffUtil.ItemCallback<ErrorPickingProductModel>() {
            override fun areItemsTheSame(oldItem: ErrorPickingProductModel, newItem: ErrorPickingProductModel) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ErrorPickingProductModel, newItem: ErrorPickingProductModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(ItemErrorPickingProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemErrorPickingProductBinding) {
                this@item.apply {
                    errorTime = errorTime?.toDate(holder.itemView.context.getString(R.string.dateFormat))
                }
                errorPickingProduct = this@item
            }
        }
    }
}