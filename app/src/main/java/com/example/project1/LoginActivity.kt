package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import android.content.Intent;
import android.view.View;
import android.widget.TextView
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


private lateinit var etPassword: EditText;
private lateinit var etUsername: EditText;
private lateinit var username: String;
private lateinit var password: String;
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)

        var etButton = findViewById<Button>(R.id.btnLogin)

      etButton.setOnClickListener{
          login(etUsername, etPassword)

      }

        this.findViewById<TextView>(R.id.tvRegisterLink).setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    private fun login(Name:EditText,Pass:EditText) {
        val userName: String = Name.getText().toString().trim()
        val password: String = Pass.getText().toString().trim()

        val call: Call<ResponseBody> = RetroFitClient
            .getInstance()
            .api
            .checkUser(User(userName, password))

        if (userName.isEmpty()) {
            Name.setError("Username is required")
            Name.requestFocus()
            return
        } else if (password.isEmpty()) {
            Pass.setError("Password is required")
            Pass.requestFocus()
            return
        }

        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == userName) {
                   // Toast.makeText(this@LoginActivity, "Successfully login", Toast.LENGTH_LONG).show()
                    var intent=Intent(this@LoginActivity, DashboardActivity::class.java)
                    intent.putExtra("Username",s)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LoginActivity, "User does not exists!", Toast.LENGTH_LONG)
                        .show()
                }


            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }

}
