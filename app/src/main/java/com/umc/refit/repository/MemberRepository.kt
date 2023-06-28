package com.umc.refit.repository

import com.umc.refit.api.RetrofitInstance.memberApi
import com.umc.refit.dto.EmailDto
import com.umc.refit.dto.EmailResDto
import retrofit2.Call

class MemberRepository() {

    //이메일 인증 API
    fun auth_email(email: EmailDto): Call<EmailResDto> {
        return memberApi.auth_email(email)
    }
}