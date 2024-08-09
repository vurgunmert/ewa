package com.vurgun.ewa

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.vurgun.ewa.data.repository.GameRepositoryImpl
import com.vurgun.ewa.data.repository.GeminiApiRepositoryImpl
import com.vurgun.ewa.presentation.gamehost.GameHostViewModel
import com.vurgun.ewa.presentation.games.match.repository.MatchGameRepositoryImpl
import com.vurgun.ewa.presentation.games.match.ui.AndroidMatchGameViewModel
import data.repository.GameRepository
import data.repository.MatchGameRepository
import domain.repository.GeminiRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EwaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EwaApplication)
            modules(gamesModule)
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

val gamesModule = module {
    single<GameRepository> { GameRepositoryImpl() } //TODO: secure api key
    single<GeminiRepository> { GeminiApiRepositoryImpl("") }
    single {
        HttpClient { //TODO: General client
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single<MatchGameRepository> { MatchGameRepositoryImpl(get(), get()) }
}

val presentationModule = module {
    viewModel { AndroidMatchGameViewModel(get()) }
    viewModel { GameHostViewModel(get()) }
}