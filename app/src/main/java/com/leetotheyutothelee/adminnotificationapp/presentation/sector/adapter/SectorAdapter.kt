package com.leetotheyutothelee.adminnotificationapp.presentation.sector.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.leetotheyutothelee.adminnotificationapp.base.BaseViewHolder
import com.leetotheyutothelee.adminnotificationapp.databinding.ItemSectorBinding
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui.ErrorPickingProductActivity
import com.leetotheyutothelee.adminnotificationapp.presentation.model.SectorModel

class SectorAdapter: ListAdapter<SectorModel, BaseViewHolder>(SECTOR_DIFF) {

    companion object{
        private val SECTOR_DIFF = object: DiffUtil.ItemCallback<SectorModel>() {
            override fun areItemsTheSame(oldItem: SectorModel, newItem: SectorModel) =
                oldItem.sectorType == newItem.sectorType

            override fun areContentsTheSame(oldItem: SectorModel, newItem: SectorModel) =
                oldItem.sectorType == newItem.sectorType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(ItemSectorBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.run item@ {
            with(holder.binding as ItemSectorBinding) {
                sectorModel = this@item
            }
            with(holder.itemView) {
                setOnClickListener {
                    context.startActivity(Intent(context, ErrorPickingProductActivity::class.java))
                }
            }
        }
    }
}