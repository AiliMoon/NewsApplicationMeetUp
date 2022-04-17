package com.example.newsapplication

import android.os.Bundle
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.view.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}