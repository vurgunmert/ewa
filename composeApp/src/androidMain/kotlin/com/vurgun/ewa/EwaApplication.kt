package com.vurgun.ewa

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.vurgun.ewa.data.repository.GameRepositoryImpl
import com.vurgun.ewa.data.repository.GeminiApiRepositoryImpl
import com.vurgun.ewa.data.repository.RemoteConfigRepository
import com.vurgun.ewa.presentation.gamehost.GameHostViewModel
import com.vurgun.ewa.presentation.games.match.repository.MatchGameRepositoryImpl
import com.vurgun.ewa.presentation.games.match.ui.AndroidMatchGameViewModel
import data.repository.GameRepository
import data.repository.MatchGameRepository
import domain.repository.GeminiRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EwaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@EwaApplication)
            modules(gamesModule)
            modules(presentationModule)
        }

        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0) //todo: prod 3600
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
    }
}

val gamesModule = module {
    single<RemoteConfigRepository> { RemoteConfigRepository() }
    single<GameRepository> { GameRepositoryImpl() }
    single<GeminiRepository> { GeminiApiRepositoryImpl(get()) }
    single<MatchGameRepository> { MatchGameRepositoryImpl(get(), get()) }
}

val presentationModule = module {
    viewModel { AndroidMatchGameViewModel(get()) }
    viewModel { GameHostViewModel(get()) }
}