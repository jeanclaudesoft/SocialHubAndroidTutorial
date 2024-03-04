package com.claudylab.socialhub.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.claudylab.socialhub.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var loginBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginBtn = findViewById(R.id.btnLogin)
        loginBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}