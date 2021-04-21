package com.rodriguez.giomar.elmeneodashboard.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.elmeneodashboard.YoutubeVideoIdUtil
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class YoutubeScreenViewModel : ViewModel() {
    private val TAG: String = "YoutubeScreenViewModel"
    val video: MutableState<YoutubeVideo> = mutableStateOf(YoutubeVideo())

    fun searchVideo(videoUrl: String) {
        var videoId: String = ""

        if(videoUrl.length == 11) {
            videoId = videoUrl
        }else {
            videoId = YoutubeVideoIdUtil.extractVideoIdFromUrl(videoUrl)!!
        }

        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideo = YoutubeVideoApiService.getVideoDetails(videoId)
            video.value = fetchedVideo
        }
    }
    fun saveVideo() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = YoutubeVideoApiService.saveVideo(video.value)
            if(response.status.value == 201){

            }
        }
    }
}