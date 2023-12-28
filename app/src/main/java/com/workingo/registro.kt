package com.workingo

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.workingo.databinding.ActivityLoginEmailBinding
import com.workingo.databinding.ActivityRegistroBinding

class registro : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var  progressDialog: ProgressDialog

    private var email = ""
    private var password = ""
    private var password2 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnRegistrar.setOnClickListener {

            validar()

        }

    }

    private fun validar(){

        email = binding.emailLogin.text.toString().trim()
        password = binding.passwordLogin.text.toString().trim()
        password2 = binding.repetiPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            binding.emailLogin.error = "Email invalido"
            binding.emailLogin.requestFocus()

        }else if(email.isEmpty()){

            binding.emailLogin.error = "Ingrese email"
            binding.emailLogin.requestFocus()

        }else if(password.isEmpty()){

            binding.passwordLogin.error = "Ingrese password"
            binding.passwordLogin.requestFocus()

        }else if(password2.isEmpty()){

            binding.repetiPassword.error = "Ingrese password"
            binding.repetiPassword.requestFocus()

        }else if (password != password2){

            binding.repetiPassword.error = "No coinciden"
            binding.repetiPassword.requestFocus()

        }else{

            registrarUsuario()

        }

    }

    private fun registrarUsuario(){

        progressDialog.setMessage("Creando Cuenta")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                llenarInfoBD()

            }
            .addOnFailureListener {e->

                progressDialog.dismiss()
                Toast.makeText(
                    this, "No se registro el usuario ${e.message}",Toast.LENGTH_SHORT
                ).show()

            }



    }

    private fun llenarInfoBD(){

        progressDialog.setMessage("Guardando informacion")

        val tiempo = Constantes.obtenerTiempo()
        val emailUsuario = firebaseAuth.currentUser!!.email
        val uiUsuario = firebaseAuth.uid

        val hashMap = HashMap<String, Any>()
        hashMap["nombre"] = ""
        hashMap["codigoTelefono"] = ""
        hashMap["Telefono"] = ""
        hashMap["urlImagenPerfil"] = ""
        hashMap["usuario"] = "Email"
        hashMap["tiempo"] = tiempo
        hashMap["email"] = "${emailUsuario}"
        hashMap["uid"] = "${uiUsuario}"
        hashMap["fecha_nacimiento"] = ""

        val ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        ref.child(uiUsuario!!)
            .setValue(hashMap)
            .addOnSuccessListener {

                progressDialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()

            }
            .addOnFailureListener {e->

                progressDialog.dismiss()
                Toast.makeText(
                    this, "No se registro debido a ${e.message}", Toast.LENGTH_SHORT
                ).show()

            }
    }

}