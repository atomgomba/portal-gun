package com.ekezet.portalgun.ui

import android.content.Context
import com.ekezet.portalgun.ui.MainActivityModule.Binder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        Binder::class,
    ]
)
object MainActivityModule {
    @Module
    abstract class Binder {
        @Binds internal abstract fun bindContext(activity: MainActivity): Context
        @Binds internal abstract fun bindRouter(router: MainRouter): MainContract.Router
    }
}
