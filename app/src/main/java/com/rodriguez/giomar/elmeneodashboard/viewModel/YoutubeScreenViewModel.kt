package com.rodriguez.giomar.elmeneodashboard.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YoutubeScreenViewModel : ViewModel() {
    val video: MutableState<YoutubeVideo> = mutableStateOf(YoutubeVideo())

    fun searchVideo() {
        CoroutineScope(Dispatchers.IO).launch {
            val fetchedVideo = YoutubeVideoApiService.getVideoDetails("OlbBcclGPK8")
            video.value = fetchedVideo
        }
    }
}