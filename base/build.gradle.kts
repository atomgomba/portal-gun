apply(from = "versioning.gradle.kts")

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.compileSdk

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildTypes.all {
        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
    }
}

dependencies {
    implementation(Deps.Kotlin.stdLib)
    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Androidx.annotation)

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.androidProcessor)

    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.moshiConverter)
    implementation(Deps.OkHttp.okHttp)
    implementation(Deps.OkHttp.loggingInterceptor)
    implementation(Deps.Moshi.moshi)
    implementation(Deps.Moshi.moshiKotlin)
    kapt(Deps.Moshi.moshiCodegen)

    implementation(Deps.jodaTime)
    implementation(Deps.timber)

    implementation(Deps.material)

    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.mockk)
}
