package com.example.shipperinboundorder.ui.activity

import ViewPager2ViewHeightAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.shipperinboundorder.R
import com.example.shipperinboundorder.adapter.SkuAdapter
import com.example.shipperinboundorder.adapter.SkuBottomSheetAdapter
import com.example.shipperinboundorder.adapter.ViewPagerAdapter
import com.example.shipperinboundorder.databinding.ActivityViewInboundOrderBinding
import com.example.shipperinboundorder.databinding.ItemLayoutSkuBottomSheetBinding
import com.example.shipperinboundorder.model.SkuListModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator


@Suppress("DEPRECATION")
class ViewInboundOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewInboundOrderBinding
    private lateinit var skuAdapter: SkuAdapter
    private lateinit var skuBottomSheetAdapter: SkuBottomSheetAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    private val addSkuList = mutableListOf<SkuListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewInboundOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager)
        val viewPager2ViewHeightAnimator = ViewPager2ViewHeightAnimator()
        viewPager2ViewHeightAnimator.viewPager2 = viewPager2

        setViewPager()
        setupTabLayout()
        setRecyclerViewAddedProducts()
        setSkuBottomSheetAdapter()

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

    private fun setSkuBottomSheetAdapter() {
        binding.txtViewAllSkuList.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetBinding = ItemLayoutSkuBottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        skuBottomSheetAdapter = SkuBottomSheetAdapter(addSkuList)
        bottomSheetBinding.rvSkuViewAllList.layoutManager = LinearLayoutManager(this)
        bottomSheetBinding.rvSkuViewAllList.adapter = skuBottomSheetAdapter

        bottomSheetDialog.show()
    }

    private fun setRecyclerViewAddedProducts() {
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))
        addSkuList.add(SkuListModel("Slice Cassata Novelty", "312313"))

        val firstFiveSkus = addSkuList.take(5)
        skuAdapter = SkuAdapter(firstFiveSkus)
        binding.rvAddedProducts.layoutManager = LinearLayoutManager(this)
        binding.rvAddedProducts.adapter = skuAdapter

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