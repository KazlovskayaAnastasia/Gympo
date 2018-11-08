package com.gmail.martsulgp.gympo.di

import android.app.Application
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.data.repository.UserDataRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

class WorkoutPartnerApplication : Application() {

    private val WorkoutPartnerModules = applicationContext {
        bean { UserDataRepositoryImpl() as UserDataRepository }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(WorkoutPartnerModules))
    }
}