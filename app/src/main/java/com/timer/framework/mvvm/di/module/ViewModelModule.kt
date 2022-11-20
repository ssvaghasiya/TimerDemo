package com.timer.framework.mvvm.di.module

import android.os.Build
import androidx.annotation.RequiresApi
import com.timer.framework.mvvm.ui.main.viewmodel.TimerViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val viewModelModule = module {
    viewModel {
        TimerViewModel(get(), get())
    }
}