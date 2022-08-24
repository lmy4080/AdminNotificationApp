package com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui.ErrorPickingProductFragment
import com.leetotheyutothelee.adminnotificationapp.presentation.model.CategoryModel

class ErrorPickingProductPagerAdapter(
    private val fragment: FragmentActivity,
    private val categories: ArrayList<CategoryModel>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        categories.size

    override fun createFragment(position: Int): Fragment {
        return ErrorPickingProductFragment.newInstance(
            categories[position].categoryType ?: ""
        )
    }
}
