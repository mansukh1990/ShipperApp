package com.example.shipperinboundorder.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shipperinboundorder.adapter.InboundOrderAdapter
import com.example.shipperinboundorder.api.RetrofitInstance
import com.example.shipperinboundorder.databinding.ActivityOrderBinding
import com.example.shipperinboundorder.model.modelapi.orderlist.Order
import com.example.shipperinboundorder.model.modelapi.orderlist.OrderListRequest
import com.example.shipperinboundorder.model.modelapi.orderlist.OrderListResponse
import com.example.shipperinboundorder.utils.SharedPrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var adapter: InboundOrderAdapter
    private var currentPage = 1
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefManager = SharedPrefManager(this)

        binding.rcOrders.layoutManager = LinearLayoutManager(this)

        setupRecyclerView()

        fetchOrders()

        binding.rcOrders.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rcOrders.canScrollVertically(1) && !isLoading) {
                    currentPage++
                    fetchOrders()
                }
            }
        })

        binding.btnInbound.setOnClickListener {
            startActivity(Intent(this, CreateInbound::class.java))
            finish()

        }
        binding.btnOutbound.setOnClickListener {}

        binding.ivBack.setOnClickListener {
            sharedPrefManager.clearToken()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        binding.rcOrders.layoutManager = LinearLayoutManager(this)
        adapter = InboundOrderAdapter(listOf())
        binding.rcOrders.adapter = adapter
    }

    private fun fetchOrders() {
        isLoading = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = sharedPrefManager.getToken()

                val request = OrderListRequest(
                    warehouse = 1,
                    customer_id = "450",
                    status = "",
                    search = "",
                    sort_order = "ASC",
                    sort_key = "order_no",
                    order_type = currentPage.toString()
                )
                 RetrofitInstance.retrofitBuilder.getOrders(token!!, request)
                    .enqueue(object : Callback<OrderListResponse> {
                        override fun onResponse(
                            call: Call<OrderListResponse>,
                            response: Response<OrderListResponse>
                        ) {
                            if (response.isSuccessful && response.body()?.status=="success") {
                                response.body()?.let {
                                    val orders  = response.body()?.data ?: listOf()
                                    adapter = InboundOrderAdapter(orders)
                                    binding.rcOrders.adapter = adapter
                                    Log.e("OrderActivity", "fetchOrders: " + response.body()!!.data)

                                }
                            } else {
                                Log.e("OrderActivity", "fetchOrders: " + response.message())
                                Toast.makeText(
                                    this@OrderActivity,
                                    "OrderActivity: ${response.message()}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                            Log.e(
                                "OrderActivity",
                                "onFailure: " + t.message + t.localizedMessage?.toString()
                            )
                            Toast.makeText(
                                this@OrderActivity,
                                "Failed to fetch orders",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    })


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@OrderActivity,
                        "OrderActivity: ${e.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}