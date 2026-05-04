plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.l17retrofitdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.l17retrofitdemo"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        /*
            Keeps Java compilation aligned with Kotlin compilation.
        */
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        /*
            Enables ActivityMainBinding from activity_main.xml.
        */
        viewBinding = true
        compose = true
    }
}

kotlin {
    /*
        Uses Java 17 as Kotlin JVM toolchain.
    */
    jvmToolchain(17)
}

dependencies {
    /*
        Android core Kotlin extensions.
    */
    implementation("androidx.core:core-ktx:1.16.0")

    /*
        ComponentActivity and ViewModel delegate support.
    */
    implementation("androidx.activity:activity-ktx:1.10.1")

    /*
        ViewModel and lifecycle-aware components.
    */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    /*
        ConstraintLayout for the main screen.
    */
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    /*
        Retrofit generates HTTP client implementations from annotated interfaces.
    */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    /*
        Gson converter maps JSON responses to Kotlin data classes.
    */
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /*
        OkHttp is the HTTP client used under Retrofit.
    */
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    /*
        Logging interceptor allows students to inspect HTTP requests and responses.
    */
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    /*
        Jetpack Compose
    */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}