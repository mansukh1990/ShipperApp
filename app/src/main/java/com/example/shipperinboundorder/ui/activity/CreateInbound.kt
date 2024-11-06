package com.example.shipperinboundorder.ui.activity

//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.shipperinboundorder.adapter.SkuAdapter
import com.example.shipperinboundorder.api.RetrofitInstance
import com.example.shipperinboundorder.databinding.ActivityCreateInboundBinding
import com.example.shipperinboundorder.databinding.DialogInboundConfrimationBinding
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
    private lateinit var addYourProductAdapter: SkuAdapter
    private val selectedSkus = mutableListOf<Pair<Sku, Int>>()
    private lateinit var sharedPrefManager: SharedPrefManager
    private val skuList = mutableListOf<Sku>()
    private lateinit var skuId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateInboundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefManager = SharedPrefManager(this)

        setRecyclerViewAddYourProduct()
        setDatePicker()
        fetchSKUsAndSetAutoComplete()
        // createInboundOrder()

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }
        binding.btnAddProduct.setOnClickListener {
            addSkuToRecyclerView()
        }
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
            if (addYourProductAdapter.isEmpty()) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "No Products Added",
                    message = "Please add at least one product to proceed",
                    confirmText = "Ok"
                )
            } else {
                if (selectedSkus.isNotEmpty()) {
                    showConfirmationDialog()
                }

            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addSkuToRecyclerView() {
        val skuName = binding.edtSelectSku.text.toString()
        val quantity = binding.edtEnterQty.text.toString().toIntOrNull()


        if (skuName.isNotEmpty() && quantity != null) {
            val sku = Sku(skuName,quantity )
            selectedSkus.add(Pair(sku, quantity))
            addYourProductAdapter.notifyDataSetChanged()

            // Remove the SKU from the AutocompleteTextView suggestions
            val adapter = binding.edtSelectSku.adapter as ArrayAdapter<String>
            val position = adapter.getPosition(skuName)
            adapter.remove(skuName)

            binding.edtSelectSku.setText("")
            binding.edtEnterQty.setText("")

        } else if (TextUtils.isEmpty(binding.edtSelectSku.text.toString())) {
            showErrorDialog(
                type = SweetAlertDialog.ERROR_TYPE,
                title = "Opps...",
                message = "Enter Valid SKU",
                confirmText = "OK"
            )
        } else if (TextUtils.isEmpty(binding.edtEnterQty.text.toString())) {
            showErrorDialog(
                type = SweetAlertDialog.ERROR_TYPE,
                title = "Opps...",
                message = "Enter Valid Qty",
                confirmText = "OK"
            )
        }
    }

    private fun createInboundOrder() {
        val token = sharedPrefManager.getToken()

        val orderNumber = binding.edtOrderNo.text.toString().toInt()
        val expectedDeliveryDate = binding.edtSelectedDate.text.toString()

        val skuIds = selectedSkus.map { it.first.id }
        val quantities = selectedSkus.map { it.second }

        val request = InboundOrderCreateRequest(
            expected_delivery_date = expectedDeliveryDate,
            external_order_no = orderNumber,
            quantity = quantities,
            remarks = "",
            sku_id = skuIds,
            warehouse = 2,
        )
        Log.e("TAG", "createInboundOrder: $skuIds")
        RetrofitInstance.retrofitBuilder.createInboundOrder("Bearer $token", request)
            .enqueue(object : Callback<InboundOrderCreateResponse> {
                override fun onResponse(
                    call: Call<InboundOrderCreateResponse>,
                    response: Response<InboundOrderCreateResponse>
                ) {
                    if (response.isSuccessful) {
                        val inboundOrderResponse = response.body()
                        if (inboundOrderResponse != null) {
                            if (inboundOrderResponse.responseCode == 200) {
                                Log.d(
                                    "API Success",
                                    "Response Code: ${inboundOrderResponse.responseCode}"
                                )
                                Log.d(
                                    "API Success",
                                    "Response Message: ${inboundOrderResponse.response}"
                                )
                                Log.d("API Success", "Status: ${inboundOrderResponse.status}")
                                Toast.makeText(
                                    applicationContext,
                                    inboundOrderResponse.response,
                                    Toast.LENGTH_SHORT
                                ).show()
                                navigateToViewInboundOrderDetails()
                            } else {
                                Log.e("API Error", "Empty response body")
                                Toast.makeText(
                                    applicationContext,
                                    "Failed to create Inbound Order",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("API Error", "Error: $errorBody")
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

    private fun navigateToViewInboundOrderDetails() {
        val intent = Intent(this, ViewInboundOrderActivity::class.java)
        intent.putExtra("order Number", binding.edtOrderNo.text.toString())
        intent.putExtra("expectedDeliveryDate", binding.edtSelectedDate.text.toString())
        intent.putExtra("comments", binding.edtEnterYourComments.text.toString())
        intent.putParcelableArrayListExtra("selectedSkus", ArrayList(selectedSkus.map { it.first }))
        intent.putIntegerArrayListExtra("quantities", ArrayList(selectedSkus.map { it.second }))
        startActivity(intent)
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
                            skuList.addAll(responseBody.data)
                            Log.e("TAG", "onResponse: "+skuList.toString(), )
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
        val skuNames = skuList.map { it.name }
        val adapter = ArrayAdapter(this, R.layout.simple_dropdown_item_1line, skuNames)
        binding.edtSelectSku.setAdapter(adapter)
        binding.edtSelectSku.setOnItemClickListener { parent, view, position, id ->
            val selectedSku = skuList[position].id
            skuId = selectedSku.toString()
        }
    }


    private fun showConfirmationDialog() {
        val confirmationDialogBinding =
            DialogInboundConfrimationBinding.inflate(LayoutInflater.from(this))


        confirmationDialogBinding.txtExtOrderValue.text = binding.edtOrderNo.text.toString()
        confirmationDialogBinding.txtEstArrivalDateValue.text =
            binding.edtSelectedDate.text.toString()
        confirmationDialogBinding.rvSku.adapter =
            SkuAdapter(selectedSkus, allowDelete = false, onDeleteClick = {})

        confirmationDialogBinding.rvSku.layoutManager = LinearLayoutManager(this)


        val builder = AlertDialog.Builder(this)
        builder.setView(confirmationDialogBinding.root)
        builder.setCancelable(true)

        val dialog = builder.create()

        confirmationDialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        confirmationDialogBinding.btnSubmit.setOnClickListener {
            createInboundOrder()
            dialog.dismiss()

        }
        confirmationDialogBinding.imgCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()

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
        addYourProductAdapter = SkuAdapter(selectedSkus, allowDelete = true) { position ->
            removeSkuFromRecyclerView(position)
        }
        binding.rvAddYourProduct.layoutManager = LinearLayoutManager(this)
        binding.rvAddYourProduct.adapter = addYourProductAdapter

    }

    private fun removeSkuFromRecyclerView(position: Int) {
        val skuName = selectedSkus[position].first.name
        selectedSkus.removeAt(position)
        addYourProductAdapter.notifyItemRemoved(position)

        // Restore SKU to AutocompleteTextView
        val adapter = binding.edtSelectSku.adapter as ArrayAdapter<String>
        adapter.add(skuName)
    }

}