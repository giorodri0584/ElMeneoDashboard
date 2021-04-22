package com.rodriguez.giomar.elmeneodashboard.screen.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SaveVideoComponent(onSaveVideo: () -> Unit) {
    Button(
        onClick = { onSaveVideo() },
    ) {
        Text("Guardar Video")
    }
}