package com.proergy.smart.apis

import retrofit2.http.GET

data class AdsItem(
    val id: Long,
    val advTitle: String,
    val advType: String,
    val advContent: String,
    val advImageUrl: String,
    val pubStatus: String
)



interface CommonApi {
    @SkipAuth
    @GET("biz/v1/biz/adv/list")
    suspend fun getAds(
    ): ApiResponse<List<AdsItem>>

//    @SkipAuth
//    @GET("biz/v1/biz/notification/list")
//    suspend fun getNotification(): Response<1>
}