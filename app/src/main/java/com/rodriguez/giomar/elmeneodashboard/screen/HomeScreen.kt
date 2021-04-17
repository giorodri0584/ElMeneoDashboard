package com.rodriguez.giomar.elmeneodashboard.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.rodriguez.giomar.el_meneo.api.YoutubeVideoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CoroutineScope(Dispatchers.IO).launch {
            YoutubeVideoApiService.getVideoDetails("OlbBcclGPK8")
        }
        return ComposeView(requireContext()).apply { 
            setContent { 
                Text(
                    text = "Hello",
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}