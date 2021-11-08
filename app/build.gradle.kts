plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

val versionMajor = 0
val versionMinor = 2
val versionPatch = 1

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
        applicationId = "com.ekezet.portalgun"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

        versionCode = 10000 * versionMajor + 100 * versionMinor + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Deps.Kotlin.stdLib)
    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.coreKtx)
    implementation(Deps.Androidx.appcompat)
    implementation(Deps.Androidx.Compose.ui)
    implementation(Deps.Androidx.Compose.preview)
    implementation(Deps.Androidx.Compose.material)
    implementation(Deps.Androidx.Compose.activity)
    implementation(Deps.Androidx.Lifecycle.runtimeKtx)

    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.compiler)
    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.androidProcessor)

    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Moshi.moshi)
    implementation(Deps.OkHttp.okHttp)

    implementation(Deps.material)
    implementation(Deps.Coil.coil)
    implementation(Deps.Coil.compose)

    implementation(Deps.timber)
    implementation(Deps.jodaTime)

    debugImplementation(Deps.Androidx.Compose.tooling)

    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.mockk)

    implementation(project(":base"))
}

kapt {
    useBuildCache = true
    correctErrorTypes = true
}
