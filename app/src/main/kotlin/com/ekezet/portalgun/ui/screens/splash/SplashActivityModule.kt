package com.ekezet.portalgun.ui.screens.splash

import android.content.Context
import com.ekezet.portalgun.ui.screens.splash.SplashActivityModule.Binder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        Binder::class,
    ]
)
object SplashActivityModule {
    @Module
    abstract class Binder {
        @Binds internal abstract fun bindContext(activity: SplashActivity): Context
        @Binds
        internal abstract fun bindViewModel(viewModel: SplashViewModel): SplashContract.ViewModel
        @Binds internal abstract fun bindRouter(router: SplashRouter): SplashContract.Router
    }
}
