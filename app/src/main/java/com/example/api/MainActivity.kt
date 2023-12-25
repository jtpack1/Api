package com.example.api

import ApiResponse
import AuthService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.api.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        //Log.e("pap","login gagal")
        val retrofit = Retrofit.Builder()
            .baseUrl(" https://6537-113-11-181-208.ngrok-free.app") // Ganti dengan URL server Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authService = retrofit.create(AuthService::class.java)

        val user = User("andy", "andy12")

        val call = authService.login(user)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse?.status == "success") {
                        Log.e("pap","login sukses")
                        binding.status.text = "sukses"
                    } else {
                        Log.e("pap","login gagal")
                        binding.status.text = "gagal"
                    }
                } else {
                    // Respons tidak berhasil
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("pap","telah gagal karena koneksi")
            }
        })

    }
}

