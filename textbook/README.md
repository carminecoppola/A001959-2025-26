# 📱 Android Textbook Exercises (Appendix E) – Kotlin Projects

This repository contains **fully implemented Android applications** based on **Appendix E of the course textbook**:

> *Mobile Programming with Android and Kotlin – A001959*

Each exercise is a **standalone Android Studio project**, following a professional software engineering approach.

---

## 📂 Repository Structure

```text
A001959-2025-26/
├── homework/          ← simple Kotlin exercises (.kt)
├── ta_exercises/      ← guided exercises
└── textbook/          ← Android projects (Appendix E)
    ├── E01_HelloWorld/
    ├── E02_Calculator/
    ├── E03_FiscalCode/
    ├── E04_WeatherForecast/
    ├── E05_AcademicCareer/
    └── E06_Breakdroid/
```

⚠️ **Important:** `textbook/` is NOT an Android project. Each `E0X_*` directory is an **independent Gradle project** — always open them individually in Android Studio.

---

## 🧩 Exercises

### ✅ E01 — Hello World

Basic Android app introducing Activity lifecycle, XML UI, View Binding and state persistence on rotation (`onSaveInstanceState`).

**Key features:** text input, "Say Hello" button, IME handling, keyboard management, rotation-safe state.

---

### ✅ E02 — Calculator

Structured calculator with MVVM architecture.

**Architecture:** Activity (UI only) + ViewModel (logic) + LiveData  
**Key features:** +−×÷, decimals, percentage, sign toggle, division-by-zero handling, GridLayout UI, state persistence on rotation.

---

### ✅ E03 — Italian Fiscal Code

App that computes the Italian codice fiscale by calling a local Python/Flask backend.

**Stack:** Retrofit + OkHttp + Coroutines + ViewModel  
**Backend:** Flask + `codicefiscale` library (run locally on port 8080)  
**Key features:** form input (cognome, nome, data, sesso, codice catastale), HTTP POST to `10.0.2.2:8080`, error handling.

> ⚠️ Requires backend running: `cd textbook/E03_FiscalCode/backend && python app.py`

---

### ✅ E04 — Weather Forecast

Meteo app using the real WRF5 API from CCMMMA lab at Università Parthenope.

**Stack:** Retrofit + OkHttp + Coroutines + ViewModel + RecyclerView  
**API:** `https://api.meteo.uniparthenope.it/products/wrf5/timeseries/{place}`  
**Key features:** city spinner (Naples, Rome, Milan, Salerno), current conditions display, horizontal hourly forecast RecyclerView, weather emoji mapping.

---

### ✅ E05 — Academic Career

Login + career app using the real Uniparthenope student API with encrypted credential storage.

**Stack:** Retrofit + OkHttp + Coroutines + AndroidViewModel + EncryptedSharedPreferences  
**API:** `https://api.uniparthenope.it/UniparthenopeApp/v1/`  
**Key features:** Basic Auth login (username ESSE3 format `aXXXXXX`), EncryptedSharedPreferences (AES256), session persistence, career summary (CFU, media), exam list with RecyclerView, logout, FLAG_SECURE screen protection.

---

### ✅ E06 — Breakdroid

Full Breakout/Arkanoid game built on a custom SurfaceView with a dedicated game loop thread.

**Key concepts:** SurfaceView + SurfaceHolder, GameThread (60 FPS loop), AABB collision detection, sealed GameState machine, lifecycle integration (pause/resume).  
**Key features:** touch-controlled paddle, multi-level progression, 3 lives, score HUD, full-screen immersive mode, colored brick grid.

---

## ⚙️ Technologies

| Technology | Used in |
|---|---|
| Kotlin | All |
| View Binding | All |
| ViewModel + LiveData | E02, E03, E04, E05 |
| Retrofit + OkHttp | E03, E04, E05 |
| Coroutines | E03, E04, E05 |
| EncryptedSharedPreferences | E05 |
| RecyclerView | E04, E05 |
| SurfaceView + Canvas | E06 |
| Python/Flask backend | E03 |

---

## 💻 Requirements

- Android Studio (latest stable)
- JDK 17+
- Emulator or physical device (API 26+)
- Python 3.9+ with pip (E03 only)

---

## 🚀 How to Open a Project

```
File → Open → textbook/E0X_ProjectName
```

Never open `textbook/` directly — it is not a Gradle project.

---

## 🚀 How to Build (Terminal)

```bash
cd textbook/E02_Calculator
./gradlew assembleDebug
```

---

## ⚠️ Common Errors & Fixes

**"Does not contain a Gradle build"** → Open `textbook/E0X_...` not `textbook/`

**ViewBinding not found** → Add `buildFeatures { viewBinding = true }` in `app/build.gradle.kts`

**Theme.Material3 not found** → Replace theme parent with `Theme.AppCompat.Light.NoActionBar`

**`kotlin-compose` plugin error** → Replace with `kotlin-android` in `libs.versions.toml` and `build.gradle.kts`

**`Cannot add extension 'kotlin'`** → AGP 9.x applies Kotlin automatically; remove `alias(libs.plugins.kotlin.android)` from `app/build.gradle.kts`

---

## 🧠 Learning Outcomes

By completing all exercises the student demonstrates:

- Building Android apps in Kotlin with clean architecture
- MVVM pattern with ViewModel and LiveData
- REST API integration with Retrofit and Coroutines
- Secure local storage with EncryptedSharedPreferences
- Real-time 2D rendering with SurfaceView
- Lifecycle-aware component management

---

## 👨‍💻 Author

Simone Perrotta  
Università degli Studi di Napoli "Parthenope"
