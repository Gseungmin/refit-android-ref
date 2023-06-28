package com.umc.refit.api

import com.umc.refit.dto.EmailDto
import com.umc.refit.dto.EmailResDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * 회원 API
 * */
interface MemberAPI {

    /*이메일 인증 API*/
    @POST("/auth/email")
    @Headers("content-type: application/json")
    fun auth_email(@Body email: EmailDto):Call<EmailResDto>
}