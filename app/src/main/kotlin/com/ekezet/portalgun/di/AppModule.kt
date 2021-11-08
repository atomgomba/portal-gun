package com.ekezet.portalgun.di

import android.app.Application
import android.content.Context
import com.ekezet.portalgun.App
import com.ekezet.portalgun.base.di.APP
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * @author kiri
 */
@Module
object AppModule {
    @Provides @Named(APP) fun provideAppContext(app: App): Context = app.applicationContext
    @Provides fun provideApplication(app: App): Application = app
}
