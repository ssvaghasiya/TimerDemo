package com.timer.framework.mvvm.ui.main.view.ui.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.ext.format
import com.timer.framework.mvvm.data.model.ButtonState
import com.timer.framework.mvvm.data.model.TimerModel
import com.timer.framework.mvvm.ui.main.viewmodel.TimerViewModel
import com.timer.framework.mvvm.R

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimerHomeScreen(viewModel: TimerViewModel) {
    val timer by viewModel.viewState.collectAsState(TimerModel())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimerHeader()
        Spacer(modifier = Modifier.height(25.dp))
        TimerTopSection(timer.timeDuration.format(), timer.remainingTime)
        Spacer(modifier = Modifier.height(25.dp))
        TimerButtons(viewModel)
    }
}

@Composable
fun TimerTopSection(time: String, remainingTime: Long) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time,
            fontSize = 60.sp,
            color = Color.White
        )
        Text(
            text = "millis: $remainingTime",
            fontSize = 60.sp,
            color = Color.White
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalAnimationApi
@Composable
fun TimerButtons(timerState: TimerViewModel) {
    val toggle by timerState.viewState.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            timerState.resetTimer()
        }) {
            Icon(painter = painterResource(R.drawable.ic_stop), contentDescription = "stop button")
        }

        ButtonLayout(timerState)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonLayout(timerState: TimerViewModel) {
    val toggle by timerState.viewState.collectAsState()
    var text = ""
    var color: Color = MaterialTheme.colors.primaryVariant
    var textColor: Color = Color.White
    when (toggle?.toggle) {
        ButtonState.START -> {
            text = "Start"
            color = MaterialTheme.colors.primaryVariant
            textColor = Color.White
        }
        ButtonState.PAUSE -> {
            text = "Pause"
            color = MaterialTheme.colors.secondary
            textColor = Color.Black
        }
        ButtonState.RESUME -> {
            text = "Resume"
            color = MaterialTheme.colors.secondaryVariant
            textColor = Color.Black
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(modifier = Modifier
            .clickable {
                timerState.resetTimer()
            }
            .padding(30.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.DarkGray)
            .fillMaxWidth()) {
            Text(
                text = "Reset", color = Color.White, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )
        }

        Box(modifier = Modifier
            .clickable {
                timerState.buttonSelection()
            }
            .padding(10.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(color)
            .fillMaxWidth()) {
            Text(
                text = text, color = textColor, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun TimerHeader() {
    Text(
        text = "Timer",
        fontSize = 30.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        style = MaterialTheme.typography.h4
    )
}

private fun isTimeLessThan10Seconds(time: Long) = time < 10000L