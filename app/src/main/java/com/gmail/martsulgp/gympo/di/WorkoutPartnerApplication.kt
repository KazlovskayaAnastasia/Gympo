package com.gmail.martsulgp.gympo.di

import android.app.Application
import com.gmail.martsulgp.gympo.data.repository.TrainingsRepository
import com.gmail.martsulgp.gympo.data.repository.TrainingsRepositoryImpl
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.data.repository.UserDataRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class WorkoutPartnerApplication : Application() {

    private val WorkoutPartnerModules = module(definition = {
        single(definition = { UserDataRepositoryImpl() as UserDataRepository })
        single(definition = { TrainingsRepositoryImpl() as TrainingsRepository })
    })

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(WorkoutPartnerModules))
    }
}