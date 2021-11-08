package com.ekezet.portalgun.ui.screens.splash

import kotlinx.coroutines.flow.Flow

internal object SplashContract {
    interface ViewModel {
        val isLoading: Flow<Boolean>
        val lastError: Flow<Throwable?>
    }

    interface Router {
        fun jumpToNextScreen()
    }
}
