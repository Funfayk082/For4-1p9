package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.login_btn)

        val loginEditText = findViewById<EditText>(R.id.login_edit)
        val passwordEditText = findViewById<EditText>(R.id.password_edit)

        button.setOnClickListener {
            val client = ApiConfig.getApiService().login(
                LoginDto(
                    loginEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            )

            client.enqueue(object: Callback<TokenDto?> {
                override fun onResponse(
                    call: Call<TokenDto?>,
                    response: retrofit2.Response<TokenDto?>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "Вы вошли в систему! Ваш токен: ${response.body()?.token}",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Анлак",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<TokenDto?>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        t.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        }

        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object:  retrofit2.Callback<Response?> {
            override fun onResponse(
                call: Call<Response?>,
                response: retrofit2.Response<Response?>
            ) {
                if (response.isSuccessful) {
                    val textView = findViewById<TextView>(R.id.my_text_view)
                    var result = ""
                    response.body()?.data?.forEach { dto ->
                        result += dto.id.toString() + ". " + dto.email + " \n\t\t" +  dto.last_name + " " + dto.first_name + "\n\n"
                    }
                    textView.text = result
                }
            }

            override fun onFailure(call: Call<Response?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "ОШИБКА", Toast.LENGTH_LONG).show()
            }
        })
    }
}