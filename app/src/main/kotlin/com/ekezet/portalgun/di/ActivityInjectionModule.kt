package com.ekezet.portalgun.di

import com.ekezet.portalgun.ui.MainActivity
import com.ekezet.portalgun.ui.MainActivityModule
import com.ekezet.portalgun.ui.screens.characterDetails.CharacterDetailsActivity
import com.ekezet.portalgun.ui.screens.characterDetails.CharacterDetailsActivityModule
import com.ekezet.portalgun.ui.screens.characters.CharactersActivity
import com.ekezet.portalgun.ui.screens.characters.CharactersActivityModule
import com.ekezet.portalgun.ui.screens.splash.SplashActivity
import com.ekezet.portalgun.ui.screens.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectionModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributeSplashActivityInjector(): SplashActivity

    @ContributesAndroidInjector(modules = [CharactersActivityModule::class])
    abstract fun contributeCharactersActivityInjector(): CharactersActivity

    @ContributesAndroidInjector(modules = [CharacterDetailsActivityModule::class])
    abstract fun contributeCharacterDetailsActivityInjector(): CharacterDetailsActivity
}
