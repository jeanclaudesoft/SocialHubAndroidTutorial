package com.claudylab.socialhub.ui.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.claudylab.socialhub.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegister: Button
    private lateinit var edtEmail: TextInputEditText
    private lateinit var edtPassword: TextInputEditText
    private lateinit var btnLogin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        btnRegister = findViewById(R.id.btnRegister)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val progressDialog  = ProgressDialog(this)
            progressDialog.setMessage("Connexion en cours")
            progressDialog.setCanceledOnTouchOutside(false)


            if (edtEmail.text.toString().isBlank()) {
                edtEmail.error = "Veuillez svp renseigner votre adresse mail"
            } else if (!edtEmail.text.toString().contains("@")) {
                edtEmail.error = "Veuillez svp renseigner une adresse mail valide"
            } else if (edtPassword.text.toString().isBlank()) {
                edtPassword.error = "Veuillez svp renseigner votre mot de passe"
            } else if (edtPassword.text.toString().length < 6) {
                edtPassword.error = "Votre mot de passe doit contenir au minimum 6 caracteres"
            } else {
                progressDialog.show()

                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            progressDialog.cancel()
                            val userUd = auth.currentUser!!.uid
                            val email = auth.currentUser!!.email
                            Toast.makeText(this, "Connexion reussie\n $email", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            progressDialog.cancel()
                            try {
                               throw it.exception!!
                            } catch (e:FirebaseAuthInvalidCredentialsException){
                                edtEmail.error = "Adresse mail ou mot de passe incorrect"
                                edtEmail.requestFocus()
                            } catch (e:Exception){
                                Toast.makeText(this, "Connexion echouée\n ${e.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }
            }
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}