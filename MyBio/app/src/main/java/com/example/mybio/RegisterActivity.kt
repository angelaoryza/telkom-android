package com.example.mybio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var  etEmail: EditText
    lateinit var etConfPass: EditText
    lateinit var uname: EditText
    private lateinit var etPass: EditText
    private lateinit var btnRegister: AppCompatButton
    lateinit var tvRedirectLogin: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        etEmail = findViewById(R.id.etEmail)
        etConfPass = findViewById(R.id.etConfPass)
        etPass = findViewById(R.id.etPass)
        btnRegister = findViewById(R.id.buttonRegister)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)
        uname = findViewById(R.id.etUname)

        auth = Firebase.auth

        btnRegister.setOnClickListener{
            signUpUser()
        }

        tvRedirectLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()
        val uname = uname.text.toString()

        if(email.isBlank() || pass.isBlank() || confirmPassword.isBlank() || uname.isBlank()) {
            Toast.makeText(this, "Please fill all the required form", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Signed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}