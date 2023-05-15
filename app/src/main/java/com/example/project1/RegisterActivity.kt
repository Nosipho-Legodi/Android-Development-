package com.example.project1






import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button
import android.widget.EditText;
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
class RegisterActivity : AppCompatActivity() {

    //private lateinit var etButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {

        //etButton = findViewById(R.id.btnRegister) as Button

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)

        var etButton = findViewById<Button>(R.id.btnRegister)


        etButton.setOnClickListener {
            registerUser()


        }

        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }



    }


    //Code for registerUser() method
    private fun registerUser() {
        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()
        if (userName.isEmpty()) {
            etUsername.setError("Username is required")
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }
        val call: Call<ResponseBody> = RetroFitClient
            .getInstance()
            .api
            .createUser(User(userName, password))

        call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>?,
                    response: Response<ResponseBody?>,
                ) {
                    var s = ""
                    try {
                        s = response.body()!!.string()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    if (s == "SUCCESS") {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Successfully registered. Please login",
                            Toast.LENGTH_LONG,
                        ).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "User already exists!",
                            Toast.LENGTH_LONG,
                        )
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                   Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })

    }
}



            //fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
              //  TODO("Not yet implemented")
            //}
       // })
    //}
//}
