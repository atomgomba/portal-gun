package com.ekezet.portalgun.base.di

import com.ekezet.portalgun.base.contracts.repos.ICharacterRepo
import com.ekezet.portalgun.base.contracts.repos.IEpisodeRepo
import com.ekezet.portalgun.base.contracts.repos.IFavoriteRepo
import com.ekezet.portalgun.base.repos.CharacterRepo
import com.ekezet.portalgun.base.repos.EpisodeRepo
import com.ekezet.portalgun.base.repos.FavoriteRepo
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        RepoModule.Binder::class,
    ]
)
object RepoModule {
    @Module
    abstract class Binder {
        @Binds internal abstract fun bindCharacterRepo(characterRepo: CharacterRepo): ICharacterRepo
        @Binds internal abstract fun bindFavoriteRepo(favoriteRepo: FavoriteRepo): IFavoriteRepo
        @Binds internal abstract fun bindEpisodeRepo(episodeRepo: EpisodeRepo): IEpisodeRepo
    }
}
