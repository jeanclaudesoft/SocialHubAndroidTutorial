package com.claudylab.socialhub.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.claudylab.socialhub.R

class MainActivity : AppCompatActivity() {
    private lateinit var registerBtn : Button
    private lateinit var loginBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerBtn = findViewById(R.id.registerBtn)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}