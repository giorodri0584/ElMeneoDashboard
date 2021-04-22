package com.rodriguez.giomar.elmeneodashboard.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.glide.GlideImage
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo
import com.rodriguez.giomar.elmeneodashboard.screen.component.*
import com.rodriguez.giomar.elmeneodashboard.viewModel.YoutubeScreenViewModel


class HomeScreen : Fragment() {
    private lateinit var model: YoutubeScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model = ViewModelProvider(this).get(YoutubeScreenViewModel::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                val video = model.video.value
                val isLoading = model.isLoading.value
                val isSavedState = model.isSavedState.value
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Video de Youtube") }) },
                    content = {
                        Column {
                            SearchVideoComponent() { videoUrl ->
                                model.searchVideo(videoUrl)
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            if (video.title.isNotEmpty() && !isLoading) {
                                VideoDetailComponent(video = video) {
                                    model.saveVideo()
                                }
                            }
                            if(isLoading){
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                ) {
                                    LoadingComponent()
                                }

                            }
                            if (isSavedState) {
                                SavedVideoDetailComponent()
                            }
                        }

                    }
                )
            }
        }
    }
}




