package com.rodriguez.giomar.elmeneodashboard.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.glide.GlideImage
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import com.rodriguez.giomar.elmeneodashboard.viewModel.YoutubeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreen : Fragment() {
    private lateinit var model: YoutubeScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model = ViewModelProvider(this).get(YoutubeScreenViewModel::class.java)
        model.searchVideo()
        return ComposeView(requireContext()).apply { 
            setContent {
                val video = model.video.value
                Scaffold(
                    topBar = { TopAppBar(title = {Text("Video de Youtube")})  },
                    content = {youtubeVideoContent(video)}
                )
        }
    }
}

@Composable
fun youtubeVideoContent(
    video: YoutubeVideo
) {
    Column() {
        GlideImage(
            data = video.videoCoverImageUrl,
            contentDescription = "My content description",
        )
        Text(
            text = video.title,
            style = MaterialTheme.typography.h6
        )
    }
}
}