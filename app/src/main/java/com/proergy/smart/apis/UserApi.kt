package com.proergy.smart.apis

import com.proergy.smart.utils.AesUtil
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class UserResponse(val id: String, val name: String)
data class LoginResponse(val username: String, val password: String)

interface UserApi {
    @SkipAuth
    @FormUrlEncoded
    @POST("auth/oauth2/token")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
        @Field("scope") scope: String = "server"
    ): Response<LoginResponse>

    @DELETE()
    suspend fun logout(): Response<Void>

    @GET("admin/user/app/info")
    suspend fun getUserInfo(): Response<UserResponse>
}
