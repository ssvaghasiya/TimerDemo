package com.timer.framework.mvvm.ui.main.viewmodel

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.timer.framework.mvvm.data.model.ButtonState
import com.timer.framework.mvvm.data.model.Status
import com.timer.framework.mvvm.data.model.TimerModel
import com.timer.framework.mvvm.data.repository.MainRepository
import com.timer.framework.mvvm.utils.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
class TimerViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _viewState = MutableStateFlow<TimerModel>(TimerModel())
    val viewState: StateFlow<TimerModel> = _viewState

    var countDown: CountDownTimer? = null

    init {
        _viewState.value = TimerModel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTime(duration: Duration) {
        countDown = object : CountDownTimer(duration.toMillis(), 10) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(seconds: Long) {
                _viewState.value = TimerModel(
                    timeDuration = Duration.ofMillis(seconds),
                    remainingTime = seconds,
                    status = Status.RUNNING,
                    toggle = ButtonState.PAUSE
                )
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish() {
                _viewState.value = _viewState.value.copy(
                    timeDuration = Duration.ZERO,
                    status = Status.FINISHED,
                    toggle = ButtonState.START
                )
            }

        }
        countDown?.start()
    }

    private fun pauseTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value.copy(
            status = Status.STARTED,
            toggle = ButtonState.RESUME
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value.copy(
            status = Status.STARTED,
            timeDuration = Duration.ofMillis(30000),
            remainingTime = 30000,
            toggle = ButtonState.START
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buttonSelection() {
        val state = _viewState.value

        when (state?.status) {
            Status.STARTED -> {
                startTime(state.timeDuration)
            }
            Status.RUNNING -> {
                pauseTimer()
            }
            Status.FINISHED -> {
                resetTimer()
            }
        }

    }
}