package com.ekezet.portalgun.ui.screens.characters

import android.content.Context
import com.ekezet.portalgun.ui.screens.characters.CharactersActivityModule.Binder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        Binder::class,
    ]
)
object CharactersActivityModule {
    @Module
    abstract class Binder {
        @Binds internal abstract fun bindContext(activity: CharactersActivity): Context
        @Binds
        internal abstract fun bindViewModel(viewModel: CharactersViewModel): CharactersContract.ViewModel
        @Binds internal abstract fun bindRouter(router: CharactersRouter): CharactersContract.Router
    }
}
