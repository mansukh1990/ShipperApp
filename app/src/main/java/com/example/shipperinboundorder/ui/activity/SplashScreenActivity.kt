package com.example.shipperinboundorder.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.shipperinboundorder.databinding.ActivitySplashScreenBinding
import com.example.shipperinboundorder.utils.SharedPrefManager

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefManager = SharedPrefManager(this)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 2000)

    }

    private fun checkLoginStatus() {
        if (sharedPrefManager.isLoggedIn()) {
            startActivity(Intent(this, CreateInbound::class.java))
            finish()
        } else {
           startActivity(Intent(this,LoginActivity::class.java))
        }
        finish()
    }
}