package com.example.shipperinboundorder.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shipperinboundorder.ui.fragment.DocumentsFragment
import com.example.shipperinboundorder.ui.fragment.GRNDocumentsFragment
import com.example.shipperinboundorder.ui.fragment.GRNFragment
import com.example.shipperinboundorder.ui.fragment.PutawayFragment
import com.example.shipperinboundorder.ui.fragment.QualityCheckFragment
import com.example.shipperinboundorder.ui.fragment.StatusLogFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GRNFragment()
            1 -> GRNDocumentsFragment()
            2 -> QualityCheckFragment()
            3 -> DocumentsFragment()
            4 -> PutawayFragment()
            5 -> StatusLogFragment()
            else -> Fragment()
        }
    }

}