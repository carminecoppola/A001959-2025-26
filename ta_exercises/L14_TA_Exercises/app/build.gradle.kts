plugins {
    /*
        Android application plugin.
        It allows Gradle to build, package and install the Android app.
    */
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    /*
        Namespace used by generated Android classes such as R and ViewBinding.
    */
    namespace = "com.example.l14viewmodellivedatademo"

    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.l14viewmodellivedatademo"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        /*
            ViewBinding generates ActivityMainBinding from activity_main.xml.
            This gives type-safe access to UI elements.
        */
        viewBinding = true
        compose = true
    }
}

dependencies {
    /*
        Kotlin extensions for Android core APIs.
    */
    implementation("androidx.core:core-ktx:1.16.0")

    /*
        Provides ComponentActivity and the by viewModels() delegate.
    */
    implementation("androidx.activity:activity-ktx:1.10.1")

    /*
        Provides ViewModel and LiveData, the main architectural components
        studied in Lesson 14.
    */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    /*
        ConstraintLayout is used for the screen layout.
    */
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    /*
        Compose dependencies.
    */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
}