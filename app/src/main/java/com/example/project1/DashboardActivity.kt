package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var welcomeText:String
        var tvWelcome:TextView

        welcomeText = "Welcome " + getIntent().getStringExtra("Username").toString()
        tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.setText( welcomeText)
    }

    }
