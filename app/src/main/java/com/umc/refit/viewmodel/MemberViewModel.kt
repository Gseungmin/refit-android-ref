package com.umc.refit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.refit.repository.MemberRepository
import com.umc.refit.dto.EmailDto
import com.umc.refit.dto.EmailResDto
import com.umc.refit.dto.ErrorDto
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//프로젝트 ViewModel
class MemberViewModel() : ViewModel() {

    //리파지토리
    private val repository = MemberRepository()

    //예외 처리 라이브데이터
    private var _error = MutableLiveData<ErrorDto>()
    val error : LiveData<ErrorDto>
        get() = _error

    //선택된 이미지 라이브데이터
    private var _email = MutableLiveData<EmailResDto>()
    val email : LiveData<EmailResDto>
        get() = _email

    //프로젝트를 등록
    fun auth_email(email: String) = viewModelScope.launch {

        val response = repository.auth_email(EmailDto(email))
        response.enqueue(object : Callback<EmailResDto> {
            override fun onResponse(call: Call<EmailResDto>, response: Response<EmailResDto>) {
                if (response.isSuccessful) {
                    //응답 성공 처리
                    Log.d("RESPONSE", response.body().toString())
                    _email.postValue(response.body())
                } else {
                    //에러 코드 처리
                    Log.d("RESPONSE", "FAIL")
                    var jsonObject = JSONObject(response.errorBody()!!.string())
                    _error.postValue(ErrorDto(
                        jsonObject.getInt("code"), jsonObject.getString("message")))
                }
            }
            override fun onFailure(call: Call<EmailResDto>, t: Throwable) {
                Log.d("ContinueFail", "FAIL")
            }
        })
    }
}