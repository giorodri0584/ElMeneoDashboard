package com.rodriguez.giomar.elmeneodashboard.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

object MyKtorClient {
    const val BASE_URL = "https://pg-app-khvrxioa8t2tcip8u3rdpfac52za71.scalabl.cloud/1/classes"
    private const val APP_ID = "sb7n1oX0C6ahV86imtNMDSEIPgo3nr5Td8RFLVRT"
    private const val REST_KEY = "itmgQULYggZVcbTzGs0GyPKO5OPDWMFIRdgTI9R3"
    val client: HttpClient by lazy {
        HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer()
            }
            defaultRequest {
                header("X-Parse-Application-Id", APP_ID)
                header("X-Parse-REST-API-Key", REST_KEY)
            }
        }

    }
    val youtubeClient: HttpClient by lazy {
        HttpClient(CIO){
            install(JsonFeature){
                serializer = GsonSerializer()
            }
        }

    }


}