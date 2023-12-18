package com.workingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.workingo.OpcionesLogin.login_email
import com.workingo.databinding.ActivityMainBinding
import com.workingo.fragmentos.cuentaFragment
import com.workingo.fragmentos.inicioFragment
import com.workingo.fragmentos.publicarFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //primer fragmento en verse en la app
        val intent = Intent(this, login_email::class.java)
        startActivity(intent)

        //
        binding.buttonNavigationView.setOnItemReselectedListener { item->

            when(item.itemId){

                R.id.ItemInicio->{
                    verfragmentInicio()
                    true
                }
                R.id.ItemPublicar->{
                    verfragmentPublicar()
                    true
                }
                R.id.ItemCuenta->{
                    verfragmentCuenta()
                    true
                }else->{

                    false

                }

            }

        }

    }

    //funciones de cada fragmento
    private fun verfragmentInicio(){

        //modificar el titulo del android:text
        binding.tituloRL.text = "Inicio"
        //se crea un variable que representa la parte logica
        val fragment = inicioFragment()
        // Hacer la transaccion en el fragmentlaya
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.fragmentL1.id, fragment, "inicioFragment")
        //hacer los cambios en el fragment
        fragmentTransition.commit()

    }

    private fun verfragmentCuenta(){

        binding.tituloRL.text = "Cuenta"

        val fragment = cuentaFragment()
        val fragmentTransition = supportFragmentManager.beginTransaction()

        fragmentTransition.replace(binding.fragmentL1.id, fragment, "cuentaFragment")
        fragmentTransition.commit()

    }

    private fun verfragmentPublicar(){

        binding.tituloRL.text = "Publicar"

        val fragment = publicarFragment()
        val fragmentTransition = supportFragmentManager.beginTransaction()

        fragmentTransition.replace(binding.fragmentL1.id, fragment, "publicarFragment")
        fragmentTransition.commit()

    }

}