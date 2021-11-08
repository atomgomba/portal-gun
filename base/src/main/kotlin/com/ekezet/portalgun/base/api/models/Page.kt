package com.ekezet.portalgun.base.api.models

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

data class Page<E>(val results: List<E>, val info: Info) {

    data class Info(val count: Int, val pages: Int, val next: String?, val prev: String?) {
        val hasNext = next != null
        val hasPrev = prev != null
        val nextPageNum: Int? by lazy { next?.getPageNum() }
        val prevPageNum: Int? by lazy { prev?.getPageNum() }

        private fun String?.getPageNum(): Int? {
            val url = this ?: return null
            val parsed = url.toHttpUrlOrNull() ?: return null
            val page = parsed.queryParameter("page") ?: return null
            return page.toIntOrNull()
        }
    }
}
