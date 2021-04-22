package com.rodriguez.giomar.elmeneodashboard.viewModel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.elmeneodashboard.YoutubeVideoIdUtil
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YoutubeScreenViewModel : ViewModel() {
    private val TAG: String = "YoutubeScreenViewModel"
    val video: MutableState<YoutubeVideo> = mutableStateOf(YoutubeVideo())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isSavedState: MutableState<Boolean> = mutableStateOf(false)
    val searchTerm: MutableState<String> = mutableStateOf("")

    fun searchVideo() {
        isLoading.value = true
        isSavedState.value = false
        var videoUrl: String = searchTerm.value
        var videoId: String = ""

        if(videoUrl.length == 11) {
            videoId = videoUrl
        }else {
            videoId = YoutubeVideoIdUtil.extractVideoIdFromUrl(videoUrl)!!
        }

        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideo = YoutubeVideoApiService.getVideoDetails(videoId)
            video.value = fetchedVideo
            isLoading.value = false
        }
    }
    fun saveVideo() {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = YoutubeVideoApiService.saveVideo(video.value)
            if(response == 201){
                video.value = YoutubeVideo()
                isLoading.value = false
                isSavedState.value = true
                searchTerm.value = ""
            }
        }
    }
}