package com.ekezet.portalgun.base.api.services

import com.ekezet.portalgun.base.api.models.Character
import com.ekezet.portalgun.base.api.models.Episode
import com.ekezet.portalgun.base.api.models.Page
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MicroverseService {
    @GET("character")
    suspend fun getCharactersByPage(
        @Query("page") page: Int = 1,
        @Query("status") status: Character.Status? = null,
    ): Page<Character>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Character

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode

    /**
     * @param ids A list of ids separated by commas
     */
    @GET("episode/{ids}")
    suspend fun getSomeEpisodes(@Path("ids") ids: String): List<Episode>
}
