package com.example.mybio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var tvRedirectSignUp: TextView
    lateinit var etUname: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: AppCompatButton

    // creating Firebase auth
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etUname = findViewById(R.id.etUname)
        etPass = findViewById(R.id.etPass)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            login()
        }

        tvRedirectSignUp.setOnClickListener {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

        finish()
        }
    }
    private fun login() {
        val uname = etUname.text.toString()
        val pass = etPass.text.toString()

        auth.signInWithEmailAndPassword(uname, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Loh In Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

}


