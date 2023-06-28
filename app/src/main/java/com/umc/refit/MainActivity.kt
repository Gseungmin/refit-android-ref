package com.umc.refit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.umc.refit.databinding.ActivityMainBinding
import com.umc.refit.viewmodel.MemberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

        //이메일 처리
        binding.authEmail.setOnClickListener {
            viewModel.auth_email(binding.email.text.toString())
            binding.email.text.clear()
        }

        //라이브 데이터 관리
        viewModel.email.observe(this) {
            Log.d("EMAIL_RESPONSE", it.code.toString())
        }

        //라이브 데이터 관리
        viewModel.error.observe(this) {
            Log.d("EMAIL_RESPONSE", it.message.toString())
            Log.d("EMAIL_RESPONSE", it.code.toString())
        }
    }
}