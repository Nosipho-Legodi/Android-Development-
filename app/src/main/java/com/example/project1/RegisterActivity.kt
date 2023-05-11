package com.example.project1






import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var etUsername: EditText
        lateinit var etPassword: EditText

       // var etButton =   findViewById(R.id.btnRegister) as Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

       etUsername= findViewById(R.id.etRUserName) as EditText
        etPassword= findViewById(R.id.etRPassword) as EditText

        var register =   findViewById(R.id.btnRegister) as Button

        register.setOnClickListener{
            val name = etUsername.getText().toString();
            Toast.makeText( this,  "Has Registered Successfully", Toast.LENGTH_LONG).show()



        }
    }
}