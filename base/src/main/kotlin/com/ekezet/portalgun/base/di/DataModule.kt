package com.ekezet.portalgun.base.di

import com.ekezet.portalgun.base.contracts.data.ICharacterDataSource
import com.ekezet.portalgun.base.contracts.data.IEpisodeDataSource
import com.ekezet.portalgun.base.contracts.data.IFavoriteDataSource
import com.ekezet.portalgun.base.data.CharacterNetworkDataSource
import com.ekezet.portalgun.base.data.EpisodeNetworkDataSource
import com.ekezet.portalgun.base.data.FavoriteMemoryDataSource
import com.ekezet.portalgun.base.di.DataModule.Binder
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module(
    includes = [
        Binder::class,
    ]
)
object DataModule {
    @Module
    abstract class Binder {
        @Binds @Named(NET) internal abstract fun bindCharacterNetworkDataSource(
            characterNetworkDataSource: CharacterNetworkDataSource,
        ): ICharacterDataSource

        @Binds @Named(MEM) internal abstract fun bindFavoriteMemoryDataSource(
            favoriteMemoryDataSource: FavoriteMemoryDataSource,
        ): IFavoriteDataSource

        @Binds @Named(NET) internal abstract fun bindEpisodeNetworkDataSource(
            episodeNetworkDataSource: EpisodeNetworkDataSource,
        ): IEpisodeDataSource
    }
}
