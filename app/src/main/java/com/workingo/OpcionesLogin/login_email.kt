package com.workingo.OpcionesLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.workingo.R
import com.workingo.databinding.ActivityLoginEmailBinding
import com.workingo.registro

class login_email : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrar.setOnClickListener {
            startActivity(Intent(this@login_email, registro::class.java))
        }

    }
}