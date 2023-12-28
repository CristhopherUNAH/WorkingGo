package com.workingo.OpcionesLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.workingo.MainActivity
import com.workingo.R
import com.workingo.databinding.ActivityLoginEmailBinding
import com.workingo.registro

class login_email : AppCompatActivity() {

    private lateinit var binding : ActivityLoginEmailBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registrar.setOnClickListener {
            startActivity(Intent(this@login_email, registro::class.java))
        }

    }

    private fun comprobarSession(){

        if(firebaseAuth.currentUser != null){

            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()

        }

    }

}