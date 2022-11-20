package com.timer.framework.mvvm.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration

data class TimerModel @RequiresApi(Build.VERSION_CODES.O) constructor(
    val timeDuration: Duration = Duration.ofSeconds(30),
    val remainingTime: Long = timeDuration.toMillis(),
    val status: Status = Status.STARTED,
    val toggle: ButtonState = ButtonState.START
)

enum class Status {
    STARTED, RUNNING, FINISHED
}

enum class ButtonState {
    START, PAUSE, RESUME
}