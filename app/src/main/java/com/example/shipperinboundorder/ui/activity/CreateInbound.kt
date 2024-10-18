package com.example.shipperinboundorder.ui.activity

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.shipperinboundorder.adapter.AddYourProductAdapter
import com.example.shipperinboundorder.api.RetrofitInstance
import com.example.shipperinboundorder.databinding.ActivityCreateInboundBinding
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.Sku
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.SkuRequest
import com.example.shipperinboundorder.model.modelapi.inboundorderskulist.SkuResponse
import com.example.shipperinboundorder.model.modelapi.inbundorderCreate.InboundOrderCreateRequest
import com.example.shipperinboundorder.model.modelapi.inbundorderCreate.InboundOrderCreateResponse
import com.example.shipperinboundorder.utils.SharedPrefManager
import com.example.shipperinboundorder.utils.Utils.showErrorDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateInbound : AppCompatActivity() {

    private lateinit var binding: ActivityCreateInboundBinding
    private lateinit var addYourProductAdapter: AddYourProductAdapter
    private lateinit var sharedPrefManager: SharedPrefManager
    private val addedSkus = mutableListOf<Pair<Sku, Int>>()
    private val availableSkus = mutableListOf<Sku>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateInboundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefManager = SharedPrefManager(this)

        setRecyclerViewAddYourProduct()
        setDatePicker()
        btnSubmit()
        fetchSKUsAndSetAutoComplete()
        createInboundOrder()

        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.btnAddProduct.setOnClickListener {
            addSkuToList()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addSkuToList() {
        val selectedSkuName = binding.edtSelectSku.text.toString()
        val quantity = binding.edtEnterQty.text.toString().toIntOrNull()


        val selectedSku = availableSkus.find { it.name == selectedSkuName }
        if (selectedSku != null && quantity != null) {
            addedSkus.add(Pair(selectedSku, quantity))
            addYourProductAdapter.notifyDataSetChanged()

            availableSkus.remove(selectedSku)
            setAutoCompleteTextView()

            binding.edtSelectSku.text.clear()
            binding.edtEnterQty.text?.clear()
        } else {
            showErrorDialog(
                type = SweetAlertDialog.ERROR_TYPE,
                title = "Opps...",
                message = "Invalid SKU or Quantity",
                confirmText = "OK"
            )
        }
    }

    private fun createInboundOrder() {
        val token = sharedPrefManager.getToken()

        if (token.isNullOrEmpty()) {
            tokenExpire()
        }
        val request = InboundOrderCreateRequest(
            expected_delivery_date = 19 / 10 / 2024,
            external_order_no = 236598,
            quantity = listOf(100),
            remarks = "test",
            sku_id = listOf(50028),
            warehouse = 2
        )
        RetrofitInstance.retrofitBuilder.createInboundOrder("Bearer$token", request)
            .enqueue(object : Callback<InboundOrderCreateResponse> {
                override fun onResponse(
                    call: Call<InboundOrderCreateResponse>,
                    response: Response<InboundOrderCreateResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            if (responseBody.responseCode == 200) {
                                Toast.makeText(
                                    applicationContext,
                                    responseBody.response,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Failed to create Inbound Order",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

                override fun onFailure(call: Call<InboundOrderCreateResponse>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "API Call Failed: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("API_ERROR", "onFailure: ${t.message}")
                }

            })
    }

    private fun fetchSKUsAndSetAutoComplete() {

        val token = sharedPrefManager.getToken()

        if (token.isNullOrEmpty()) {
            tokenExpire()
        }
        val request = SkuRequest(
            warehouse = 2,
            search = "",
            exclude_sku_ids = listOf(16, 18, 12)
        )
        RetrofitInstance.retrofitBuilder.getSkus("Bearer $token", request)
            .enqueue(object : Callback<SkuResponse> {
                override fun onResponse(call: Call<SkuResponse>, response: Response<SkuResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val responseBody = response.body()
                        if (responseBody?.responseCode == 200) {
                            availableSkus.addAll(responseBody.data)
                            setAutoCompleteTextView()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Failed to fetch SKUs",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }

                override fun onFailure(call: Call<SkuResponse>, t: Throwable) {
                    Log.e("API_ERROR", "onFailure: ${t.message}")
                    Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    private fun setAutoCompleteTextView() {
        val skuNames = availableSkus.map { it.name }
        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, skuNames)
        binding.edtSelectSku.setAdapter(adapter)
    }

    private fun btnSubmit() {
        binding.btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtOrderNo.text.toString())) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "Opps...",
                    message = "Please enter External Order No",
                    confirmText = "OK"
                )
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(binding.edtSelectedDate.text.toString())) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "Opps...",
                    message = "Please select date",
                    confirmText = "OK"
                )
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(binding.edtEnterYourComments.text.toString())) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "Opps...",
                    message = "Please enter comments",
                    confirmText = "OK"
                )
                return@setOnClickListener
            }
        }
    }

    private fun tokenExpire() {
        showErrorDialog(
            type = SweetAlertDialog.WARNING_TYPE,
            title = "Warning",
            message = "Please login again",
            confirmText = "Ok",
            onConfirm = {
                startActivity(Intent(this, LoginActivity::class.java))
                sharedPrefManager.clearToken()
            })
    }

    private fun setDatePicker() {
        val currentDate = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        binding.edtSelectedDate.setText(dateFormat.format(currentDate.time))

        binding.edtSelectedDate.setOnClickListener {
            showDatePicker()
        }

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Update TextInputEditText with selected date
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.edtSelectedDate.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set minimum date to today to disable past dates
        datePicker.datePicker.minDate = calendar.timeInMillis
        datePicker.show()
    }


    private fun setRecyclerViewAddYourProduct() {
        addYourProductAdapter = AddYourProductAdapter(addedSkus)
        binding.rvAddYourProduct.layoutManager = LinearLayoutManager(this)
        binding.rvAddYourProduct.adapter = addYourProductAdapter

        fetchSKUsAndSetAutoComplete()
    }

}