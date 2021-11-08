package com.ekezet.portalgun.base.data.models

import com.ekezet.portalgun.base.data.models.CharacterItem.Status
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.Test

internal class CharacterItemTest {
    @Test
    fun `when episodeUrls is not empty then episodeIds should be the episode ids as a comma-separated list`() {
        // given
        val range = 1.until(5)
        val episodeUrls = range.map { "http://example.com/api/item/$it" }
        val expectedResult: IntArray = range.toList().toIntArray()

        // when
        val subject = CharacterItem(1, "John Doe", Status.ALIVE, "Human", mockk(), "", episodeUrls)

        // then
        assertThat(subject.episodeIds).isEqualTo(expectedResult)
    }

    @Test
    fun `when episodeUrls is empty then episodeIds should be empty`() {
        // given
        val episodeUrls = emptyList<String>()
        val expectedResult: IntArray = intArrayOf()

        // when
        val subject = CharacterItem(1, "John Doe", Status.ALIVE, "Human", mockk(), "", episodeUrls)

        // then
        assertThat(subject.episodeIds).isEqualTo(expectedResult)
    }

    @Test
    fun `when episodeUrls contains invalid URL then it should be ignored`() {
        // given
        val range = 1.until(5)
        val episodeUrls = range.map { "http://example.com/api/item/$it" }.toMutableList()
        episodeUrls.add(1, "/")
        episodeUrls.add("http://example.com/api/item/")
        val expectedResult: IntArray = range.toList().toIntArray()

        // when
        val subject = CharacterItem(1, "John Doe", Status.ALIVE, "Human", mockk(), "", episodeUrls)

        // then
        assertThat(subject.episodeIds).isEqualTo(expectedResult)
    }

    @Test
    fun `episodeIds should not contain duplicates`() {
        // given
        val range = 1.until(5)
        val episodeUrls = range.map { "http://example.com/api/item/$it" }.toMutableList()
        episodeUrls.add(1, "/")
        episodeUrls.add("http://example.com/api/item/2")
        val expectedResult: IntArray = range.toList().toIntArray()

        // when
        val subject = CharacterItem(1, "John Doe", Status.ALIVE, "Human", mockk(), "", episodeUrls)

        // then
        assertThat(subject.episodeIds).isEqualTo(expectedResult)
    }
}
