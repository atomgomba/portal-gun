package com.ekezet.portalgun.base

/**
 * @author kiri
 */
sealed class OpState<T> constructor(
    open val data: T?,
    open val throwable: Throwable?,
) {
    class Idle<T> : OpState<T>(null, null)
    class Loading<T> : OpState<T>(null, null)
    class Success<T>(override val data: T) : OpState<T>(data, null)
    class Failure<T>(override val throwable: Throwable) : OpState<T>(null, throwable)

    companion object {
        fun <T> idle(): Idle<T> = Idle()
        fun <T> loading(): Loading<T> = Loading()
        fun <T> success(data: T): Success<T> = Success(data)
        fun <T> failure(throwable: Throwable): Failure<T> = Failure(throwable)
    }
}
