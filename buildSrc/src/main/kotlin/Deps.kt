object Deps {
    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val annotation = "androidx.annotation:annotation:${Versions.annotation}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
            const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        }

        object Lifecycle {
            const val runtimeKtx =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
        }
    }

    object Kotlin {
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    }

    object OkHttp {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
        const val coroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    }

    const val material = "com.google.android.material:material:${Versions.material}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
}
