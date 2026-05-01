/*
Problem 4 - Gradle Basics

Obiettivo:
- Comprendere il ruolo di Gradle in un progetto Android.

Spiegazione:
- Gradle è il sistema di build usato da Android.
- Si occupa di:
  - compilare il codice
  - gestire dipendenze
  - configurare SDK
  - generare APK
  - gestire build variants

Esempio app/build.gradle.kts:

android {
    namespace = "com.example.helloworld"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.helloworld"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
}

Edge cases:
- Non ci sono input.
- In un progetto reale, versioni incompatibili o dipendenze mancanti possono causare errori di build.

Come eseguirlo:
kotlinc P4_GradleBasics.kt -include-runtime -d P4_GradleBasics.jar && java -jar P4_GradleBasics.jar
*/

fun main() {
    println("Gradle basics — solution")
}