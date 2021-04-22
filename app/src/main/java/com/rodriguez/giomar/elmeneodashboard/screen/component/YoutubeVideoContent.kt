package com.rodriguez.giomar.elmeneodashboard.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.GlideImage
import com.rodriguez.giomar.elmeneodashboard.model.YoutubeVideo

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