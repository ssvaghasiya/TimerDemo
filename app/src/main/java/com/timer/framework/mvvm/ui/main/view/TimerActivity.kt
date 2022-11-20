package com.timer.framework.mvvm.ui.main.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.timer.framework.mvvm.ui.main.view.ui.theme.MVVMArchitectureAndroidBeginnersTheme
import com.timer.framework.mvvm.ui.main.view.ui.theme.MyTheme
import com.timer.framework.mvvm.ui.main.view.ui.compose.TimerHomeScreen
import com.timer.framework.mvvm.ui.main.viewmodel.TimerViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity2 : ComponentActivity() {
    private val timerViewModel: TimerViewModel by viewModel()

    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMArchitectureAndroidBeginnersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF101010),
                ) {
                    MyTheme {
                        TimerHomeScreen(viewModel = timerViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMArchitectureAndroidBeginnersTheme {
        Greeting("Android")
    }
}

