package com.example.city.Inicio

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.city.Modelos.MainActivity
import com.example.city.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*


internal enum class ProviderType{

    BASIC,
    GOOGLE

}

internal class HomeActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

        //Guardado de Datos
        val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        //Entrar al Main
        entrarButton.setOnClickListener{

            val intent:Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }


    fun setup(email: String, provider: String) {

        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider
        logOutButton.setOnClickListener {

            //Borrado de Datos
            val prefs: SharedPreferences.Editor = getSharedPreferences(getString(R.string.prefs_file), MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }


        saveButton.setOnClickListener {

            db.collection("users").document(email).set(
                    hashMapOf("provider" to provider,
                            "address" to addressTextView.text.toString(),
                            "phone" to phoneTextView.text.toString())
            )

        }

        getButton.setOnClickListener {

            db.collection("users").document(email).get().addOnSuccessListener {

                addressTextView.setText(it.get("address") as String)
                phoneTextView.setText(it.get("phone") as String)

            }

        }

        deleteButton.setOnClickListener {

            db.collection("users").document(email).delete()

        }

    }

}