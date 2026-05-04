plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.l11intentsdemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.l11intentsdemo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
}