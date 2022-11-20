package com.timer.framework.mvvm.di.module

import com.timer.framework.mvvm.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}