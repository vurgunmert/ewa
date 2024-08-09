package com.vurgun.ewa.presentation.gameconsole

import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.controllers.RiveFileController
import app.rive.runtime.kotlin.core.EventType
import app.rive.runtime.kotlin.core.RiveEvent
import com.vurgun.ewa.R

@Composable
fun GameConsoleScreen(modifier: Modifier = Modifier)  {

    val eventListener = object : RiveFileController.RiveEventListener {
        override fun notifyEvent(event: RiveEvent) {
            when (event.type) {
                EventType.OpenURLEvent -> {
                    Log.i("RiveEvent", "Open URL Rive event: ${event.data}")
                }
                EventType.GeneralEvent -> {
                    Log.i("RiveEvent", "General Rive event")
                }
            }
            Log.i("RiveEvent", "name: ${event.name}")
            Log.i("RiveEvent", "type: ${event.type}")
            Log.i("RiveEvent", "properties: ${event.properties}")
            // `data` contains all information in the event
            Log.i("RiveEvent", "data: ${event.data}");
        }
    }

    AndroidView(
        modifier = modifier.background(Color.Black),
        factory = { ctx ->
            RiveAnimationView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                setRiveResource(
                    R.raw.ewa_game_console,
                    autoplay = true,
                    stateMachineName = "GameConsoleStateMachine"
                )
                controller.addEventListener(eventListener)
            }
        }
    )
}
