package com.ekezet.portalgun.base.di

import com.ekezet.portalgun.base.api.services.MicroverseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {
    @Provides @Singleton fun provideMicroverseService(retrofit: Retrofit): MicroverseService =
        retrofit.create(MicroverseService::class.java)
}
