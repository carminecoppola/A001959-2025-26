plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.l24securereleasechecklist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.l24securereleasechecklist"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        debug {
            /**
             * Debug builds are intentionally debuggable.
             * The checklist will report this as acceptable only during development.
             */
            isDebuggable = true
        }

        release {
            /**
             * Release builds must never be debuggable.
             * This is one of the core checks in the Lesson 24 pre-release checklist.
             */
            isDebuggable = false

            /**
             * R8 minification and resource shrinking are enabled for production.
             * This supports binary hardening and reduces reverse engineering surface.
             */
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
}