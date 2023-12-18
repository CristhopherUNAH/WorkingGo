package com.workingo.OpcionesLogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.workingo.R
import com.workingo.databinding.ActivityLoginEmailBinding

class login_email : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}