package com.proergy.smart.apis

import com.proergy.smart.network.HttpClient
data class ApiResponse<T>(
    val code: Int,
    val msg: String?,
    val msgCode: String?,
    val data: T
)

object Http {
    val user by lazy { HttpClient.service<UserApi>() }
    val common by lazy { HttpClient.service<CommonApi>() }
}
