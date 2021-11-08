package com.ekezet.portalgun.di

import com.ekezet.portalgun.App
import com.ekezet.portalgun.base.di.BaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityInjectionModule::class,
        AppModule::class,
        BaseModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}
