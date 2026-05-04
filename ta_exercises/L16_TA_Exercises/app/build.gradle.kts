plugins {
    alias(libs.plugins.android.application)
}

kotlin {
    jvmToolchain(17)
}

android {
    /*
        Namespace used by generated Android classes such as R and ViewBinding.
    */
    namespace = "com.example.l16sharedpreferencesdemo"

    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.l16sharedpreferencesdemo"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        /*
            Java 17 keeps Java and Kotlin compilation targets aligned.
            This avoids JVM target mismatch errors in modern Android projects.
        */
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        /*
            ViewBinding generates ActivityMainBinding from activity_main.xml.
        */
        viewBinding = true
    }
}

dependencies {
    /*
        Kotlin extensions for Android core APIs.
        It also provides the SharedPreferences edit { } KTX extension.
    */
    implementation(libs.androidx.core.ktx)

    /*
        Provides ComponentActivity and modern Activity APIs.
    */
    implementation(libs.androidx.activity.ktx)

    /*
        ConstraintLayout is used for the screen layout.
    */
    implementation(libs.androidx.constraintlayout)
}