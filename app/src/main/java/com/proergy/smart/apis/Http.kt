package com.proergy.smart.apis

import com.proergy.smart.network.HttpClient

object Http {
    val user by lazy { HttpClient.service<com.proergy.smart.apis.UserApi>() }
}
