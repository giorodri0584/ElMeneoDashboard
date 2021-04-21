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
import io.ktor.http.*

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
    suspend fun saveVideo(video: YoutubeVideo) : HttpResponse {
        return MyKtorClient.client.post("${MyKtorClient.BASE_URL}/YoutubeVideos") {
            //contentType(ContentType.Application.Json)
            body = video
        }
    }
    suspend fun getVideoDetails(videoId: String): YoutubeVideo {
        val response: HttpResponse = MyKtorClient.youtubeClient.get("https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v=$videoId&format=json")
        val videoObject: JsonObject = gson.fromJson(response.readText(), JsonObject::class.java)

        val channelId: String = videoObject.get("author_name").toString().removePrefix("https://www.youtube.com/channel/")
        val channelImageUrl = getchannelImageUrl(channelId = channelId)
        return YoutubeVideo(
            videoId = videoId,
            title = videoObject.get("title").asString,
            videoCoverImageUrl = videoObject.get("thumbnail_url").asString,
            channelName = videoObject.get("author_name").asString,
            channelImageUrl = channelImageUrl,
            channelUrl = videoObject.get("author_url").asString,
        )
    }
    private suspend fun getchannelImageUrl(channelId: String) : String {
        val response: HttpResponse = MyKtorClient.youtubeClient.get(
            "https://www.googleapis.com/youtube/v3/search?q=${channelId}&part=snippet&key=AIzaSyBQBEouY2lIeM1hFBqDvxgEIv5djFQOl9I",
        )
        val jsonObject: JsonObject = gson.fromJson(response.readText(), JsonObject::class.java)
        val items = jsonObject.get("items").asJsonArray
        val channelObject = items[0].asJsonObject

        return channelObject
            .get("snippet").asJsonObject
            .get("thumbnails").asJsonObject
            .get("default").asJsonObject
            .get("url").asString
    }

}