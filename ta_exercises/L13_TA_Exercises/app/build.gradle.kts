plugins {
    /*
        Android application plugin.
        It allows Gradle to build and package the Android app.
    */
    alias(libs.plugins.android.application)

    /*
        Kotlin plugin for Android development.
    */
    alias(libs.plugins.kotlin.android)

    /*
        Compose Compiler plugin for Kotlin 2.0+
    */
    alias(libs.plugins.kotlin.compose)
}

android {
    /*
        Namespace used for generated Android classes such as R and ViewBinding.
    */
    namespace = "com.example.l13recyclerviewdemo"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.l13recyclerviewdemo"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        /*
            ViewBinding generates binding classes from XML layouts.
            In this project it generates ActivityMainBinding and ItemStudentBinding.
        */
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    /*
        Kotlin extensions for Android core APIs.
    */
    implementation(libs.androidx.core.ktx)

    /*
        Provides ComponentActivity and modern Android Activity APIs.
    */
    implementation(libs.androidx.activity.ktx)

    /*
        RecyclerView is the main component studied in Lesson 13.
    */
    implementation(libs.androidx.recyclerview)

    /*
        ConstraintLayout is used for the main screen layout.
    */
    implementation(libs.androidx.constraintlayout)

    /*
        Compose dependencies to fix "Unresolved reference 'compose'" in Color.kt
    */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}
