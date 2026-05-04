plugins {
    /*
        Android application plugin.
    */
    id("com.android.application")

    /*
        Kotlin Android plugin.
    */
    id("org.jetbrains.kotlin.android")

    /*
        Kotlin Symbol Processing.
        Room uses KSP to generate database implementation code.
    */
    id("com.google.devtools.ksp")

    /*
        Compose Compiler plugin (for Kotlin 2.0+).
    */
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}

android {
    namespace = "com.example.l15roomdatabasedemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.l15roomdatabasedemo"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        /*
            Enables generated binding classes for XML layouts.
        */
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    /*
        Kotlin extensions for Android core APIs.
    */
    implementation("androidx.core:core-ktx:1.16.0")

    /*
        Activity KTX provides ComponentActivity and useful delegates.
    */
    implementation("androidx.activity:activity-ktx:1.10.1")

    /*
        Lifecycle libraries used for ViewModel, LiveData and asLiveData().
    */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    /*
        RecyclerView displays the list of database items.
    */
    implementation("androidx.recyclerview:recyclerview:1.4.0")

    /*
        ConstraintLayout is used for the main screen layout.
    */
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    /*
        Room runtime and Kotlin extensions.
        room-ktx adds coroutine and Flow support.
    */
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    /*
        Room compiler generates the implementation of DAO and Database classes.
    */
    ksp("androidx.room:room-compiler:2.6.1")

    /*
        Compose dependencies.
    */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}
