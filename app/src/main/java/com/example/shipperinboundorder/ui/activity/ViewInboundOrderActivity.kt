@file:Suppress("DEPRECATION")

package com.example.shipperinboundorder.ui.activity

import ViewPager2ViewHeightAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.adapter.SkuAdapter
import com.example.shipperinboundorder.adapter.SkuBottomSheetAdapter
import com.example.shipperinboundorder.adapter.ViewPagerAdapter
import com.example.shipperinboundorder.databinding.ActivityViewInboundOrderBinding
import com.example.shipperinboundorder.databinding.ItemLayoutSkuBottomSheetBinding
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


class ViewInboundOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewInboundOrderBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var skuAdapter: SkuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewInboundOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager)
        val viewPager2ViewHeightAnimator = ViewPager2ViewHeightAnimator()
        viewPager2ViewHeightAnimator.viewPager2 = viewPager2

        getIntentData()
        setViewPager()
        setupTabLayout()

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, CreateInbound::class.java))
            finish()
        }

    }

    private fun getIntentData() {
        val orderNumber = intent.getStringExtra("orderNumber")
        val expectedDeliveryDate = intent.getStringExtra("expectedDeliveryDate")
        val comments = intent.getStringExtra("comments")
        val selectedSkus = intent.getParcelableArrayListExtra<Sku>("selectedSkus")
        val quantities = intent.getIntegerArrayListExtra("quantities")

        binding.edtOrderNo.setText(orderNumber)
        binding.edtSelectedDate.setText(expectedDeliveryDate)
        binding.edtEnterYourComments.setText(comments)

        setupRecyclerView(selectedSkus!!, quantities!!)

        if (selectedSkus.size > 5) {
            binding.txtViewAllSkuList.visibility = View.VISIBLE
            binding.txtViewAllSkuList.setOnClickListener {
                showBottomSheetDialog(selectedSkus, quantities)
            }
        } else {
            binding.txtViewAllSkuList.visibility = View.GONE
        }

    }

    private fun setupRecyclerView(skus: List<Sku>, quantities: List<Int>) {
        val firstFiveSkus = skus.take(5).mapIndexed { index, sku -> sku to quantities[index] }
        skuAdapter = SkuAdapter(firstFiveSkus.toMutableList(), allowDelete = false, onDeleteClick = {})
        binding.rvAddedProducts.layoutManager = LinearLayoutManager(this)
        binding.rvAddedProducts.adapter = skuAdapter
    }

    private fun showBottomSheetDialog(skus: List<Sku>, quantities: List<Int>) {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = ItemLayoutSkuBottomSheetBinding.inflate(layoutInflater)

        val recyclerView = bottomSheetBinding.rvSkuViewAllList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SkuAdapter(
            skus.mapIndexed { index, sku -> sku to quantities[index] }.toMutableList(),
            allowDelete = false, onDeleteClick = {}
        )
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetDialog.show()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.str_grn)
                }

                1 -> {
                    tab.text = getString(R.string.str_grn_document)
                }

                2 -> {
                    tab.text = getString(R.string.str_quality_check)
                }

                3 -> {
                    tab.text = getString(R.string.str_docum)
                }

                4 -> {
                    tab.text = getString(R.string.str_putaway)
                }

                5 -> {
                    tab.text = getString(R.string.str_status_log)
                }
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

    }


    private fun setViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))

            }
        })
    }

    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem -= 1
        }

    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}