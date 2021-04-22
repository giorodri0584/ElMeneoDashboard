package com.rodriguez.giomar.elmeneodashboard.screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchVideoComponent(onSearchVideo: (String) -> Unit) {
    //https://www.youtube.com/watch?v=bngsrbFI2UY
    val textState = remember { mutableStateOf(TextFieldValue(text = "")) }
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