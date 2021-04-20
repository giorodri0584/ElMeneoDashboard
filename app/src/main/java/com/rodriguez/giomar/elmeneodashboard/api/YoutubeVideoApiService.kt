package com.rodriguez.giomar.el_meneo.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.rodriguez.giomar.elmeneodashboard.api.MyKtorClient
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object YoutubeVideoApiService {
    private const val TAG = "YoutubeVideoApiService"
    private val gson: Gson = Gson()
    suspend fun getAllVideos() {
//        val response: HttpResponse = MyKtorClient.client.get("${MyKtorClient.BASE_URL}/YoutubeVideos?order=-updatedAt")
//        //Log.d(TAG, response.readText())
//        val json = gson.fromJson(response.readText(), JsonObject::class.java)
//        val videosString = json.get("results")
//        val videosArray = gson.fromJson(videosString, Array<YoutubeVideo>::class.java)
//        return ArrayList(videosArray.toMutableList())
        getVideoDetails("OlbBcclGPK8")
    }
    suspend fun getVideoDetails(videoId: String) {
        val response: HttpResponse = MyKtorClient.youtubeClient.get("https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=$videoId&format=json")
        val jsonObject: JsonObject = gson.fromJson(response.readText(), JsonObject::class.java)

        Log.d(TAG, jsonObject.get("title").toString())
        getchannelImageUrl(jsonObject.get("author_name").toString())
    }
    suspend fun getchannelImageUrl(channelId: String) {
        val response: HttpResponse = MyKtorClient.youtubeClient.get(
            "https://www.googleapis.com/youtube/v3/search?q=${channelId}&part=snippet&key=AIzaSyBQBEouY2lIeM1hFBqDvxgEIv5djFQOl9I",
        )
        val jsonArray: JsonArray = gson.fromJson(response.readText(), JsonArray::class.java)
        //val jsonObject: JsonObject = jsonArray.get(0)

        Log.d(TAG, jsonArray.toString())
    }
}