package com.ekezet.portalgun.ui.screens.characterDetails

import android.content.Context
import com.ekezet.portalgun.ui.screens.characterDetails.CharacterDetailsActivityModule.Binder
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        Binder::class,
    ]
)
object CharacterDetailsActivityModule {
    @Module
    abstract class Binder {
        @Binds internal abstract fun bindContext(activity: CharacterDetailsActivity): Context

        @Binds
        internal abstract fun bindViewModel(viewModel: CharacterDetailsViewModel): CharacterDetailsContract.ViewModel
        @Binds
        internal abstract fun bindRouter(router: CharacterDetailsRouter): CharacterDetailsContract.Router
    }
}
