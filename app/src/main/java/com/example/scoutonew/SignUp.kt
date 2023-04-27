package com.example.scoutonew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.scoutonew.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        firebaseAuth = FirebaseAuth.getInstance()
        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        /*binding.jumpToLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }*/

        binding.signupbinding = this

        binding.btnSignUp.setOnClickListener {
            val name = binding.nameSignup.text.toString()
            val email = binding.emailSignup.text.toString()
            val password = binding.passwordSignup.text.toString()
            val confermpassword = binding.conPassSignup.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confermpassword.isNotEmpty() && password == confermpassword) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "you need to login",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

            } else {
                Toast.makeText(this, "check email and password", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun movetologin(){
        startActivity(Intent(this,Login::class.java))
    }
}