package com.rodriguez.giomar.elmeneodashboard.screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo

@Composable
fun VideoDetailComponent(
    video: YoutubeVideo,
    onSaveVideo: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        YoutubeVideoContent(video)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SaveVideoComponent {
                onSaveVideo()
            }
        }
    }

}