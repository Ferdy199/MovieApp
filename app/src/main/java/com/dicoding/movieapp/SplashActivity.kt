package com.dicoding.movieapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movieapp.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding : ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.setBackgroundColor(resources.getColor(R.color.dark_blue))

        Glide.with(this).load(R.drawable.mvlx_logofix).apply(RequestOptions().override(600, 400)).centerCrop().into(binding.splashImg)
        supportActionBar?.hide()

        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}