package com.umc.refit.dto

import com.google.gson.annotations.SerializedName


/*이메일 Dto*/
data class EmailResDto (
        @SerializedName("code")
        var code : String?="",
)