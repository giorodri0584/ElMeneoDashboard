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
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Video de Youtube") }) },
                    content = {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            SearchVideoComponent() { videoUrl ->
                                model.searchVideo(videoUrl)
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            if (video.title.isNotEmpty()) {
                                YoutubeVideoContent(video)
                                Spacer(modifier = Modifier.padding(8.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    SaveVideoComponent {
                                        model.saveVideo()
                                    }
                                }
                            }
                        }

                    }
                )
            }
        }
    }
}

@Composable
fun SaveVideoComponent(onSaveVideo: () -> Unit) {
    Button(
        onClick = { onSaveVideo() },
    ) {
        Text("Guardar Video")
    }
}
@Composable
fun SearchVideoComponent(onSearchVideo: (String) -> Unit) {
    //https://www.youtube.com/watch?v=bngsrbFI2UY
    val textState = remember { mutableStateOf(TextFieldValue(text = "s5yRZOQ3EWI")) }
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = textState.value,
            onValueChange = { textState.value = it }
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp) )
        Button(
            onClick = { onSearchVideo(textState.value.text) },
        ) {
            Text("Buscar")
        }
    }
}
@Composable
fun YoutubeVideoContent(
    video: YoutubeVideo
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Column(

        ) {
            GlideImage(
                data = video.videoCoverImageUrl,
                contentDescription = "My content description",
                fadeIn = true
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

