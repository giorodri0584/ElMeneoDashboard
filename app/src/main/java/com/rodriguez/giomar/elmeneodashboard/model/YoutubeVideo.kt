package com.rodriguez.giomar.elmeneodashboard.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class YoutubeVideo(
    @SerializedName("videoId")
    var videoId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("videoCoverImageUrl")
    var videoCoverImageUrl: String,
    @SerializedName("channelName")
    var channelName: String,
    @SerializedName("channelImageUrl")
    var channelImageUrl: String,
    @SerializedName("channelUrl")
    var channelUrl: String,
    @SerializedName("likesCount")
    var likesCount: Int = 0,
    @SerializedName("viewCount")
    var viewCount: Int = 0,
    @SerializedName("commentsCount")
    var commentsCount: Int = 0
) : Parcelable