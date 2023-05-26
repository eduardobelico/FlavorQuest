package com.example.flavorquest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flavorquest.BuildConfig
import com.example.flavorquest.R
import com.example.flavorquest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}