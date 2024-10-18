package com.example.shipperinboundorder.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.shipperinboundorder.api.RetrofitInstance
import com.example.shipperinboundorder.databinding.ActivityLoginBinding
import com.example.shipperinboundorder.model.modelapi.login.LoginRequest
import com.example.shipperinboundorder.model.modelapi.login.LoginResponse
import com.example.shipperinboundorder.utils.SharedPrefManager
import com.example.shipperinboundorder.utils.Utils.showErrorDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPrefManager
        sharedPrefManager = SharedPrefManager(this)

        binding.relContinue.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "Oppss...",
                    message = "Please enter email",
                    confirmText = "Ok"
                )
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                showErrorDialog(
                    type = SweetAlertDialog.ERROR_TYPE,
                    title = "Opps...",
                    message = "Please enter password",
                    confirmText = "Ok"
                )
                return@setOnClickListener
            } else {
                login(email, password)
            }
        }
    }

    private fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        RetrofitInstance.retrofitBuilder.login(loginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful && response.body()?.status == "success") {
                        val token = response.body()?.data?.token

                        token?.let {
                            sharedPrefManager.saveToken(it)
                            showErrorDialog(
                                type = SweetAlertDialog.SUCCESS_TYPE,
                                title = "Success",
                                message = "Login Successfully",
                                confirmText = "Ok",
                                onConfirm = {
                                    startActivity(Intent(this@LoginActivity,CreateInbound::class.java))
                                    finish()
                                }
                            )
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Login Failed: ${response.body()?.response}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }
}