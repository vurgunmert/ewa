package com.vurgun.ewa

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.vurgun.ewa.presentation.games.matchgame.MatchGameRepositoryImpl
import com.vurgun.ewa.presentation.games.matchgame.ui.AndroidMatchGameViewModel
import data.repository.MatchGameRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EwaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val presentationModule = module {
            viewModel { AndroidMatchGameViewModel(get()) }
        }

        startKoin {
            androidContext(this@EwaApplication)
            modules(matchGameModule)
            modules(presentationModule)
        }

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("welcome_message" to "Welcome to EWA"))
    }
}

val matchGameModule = module {
    single<MatchGameRepository> { MatchGameRepositoryImpl() }
    viewModel { AndroidMatchGameViewModel(get()) }
}