# 📱 Android Textbook Exercises (Appendix E) – Kotlin Projects

This repository contains a collection of **fully implemented Android applications** based on **Appendix E of the course textbook**:

> *Mobile Programming with Android and Kotlin – A001959*

Each exercise is developed as a **standalone Android Studio project**, following a professional software engineering approach.

---

## 📂 Repository Structure

```text
textbook/
├── E01_HelloWorld/
├── E02_Calculator/
├── E03_...
├── E04_...
├── ...
```

⚠️ **Important**
The `textbook/` folder is NOT an Android project.
Each `E0X_*` directory is an **independent Gradle project**.

---

## 🎯 Project Philosophy

The exercises are not simple coding tasks, but are designed to simulate **real-world Android development**:

* Clean and modular architecture
* Separation of concerns (UI vs logic)
* Lifecycle-aware components
* Robust error handling
* Code suitable for academic evaluation



---

## 🧩 Exercises Description

### ✅ E01 — HelloWorld

A basic Android application introducing:

* Activity lifecycle
* XML-based UI
* User input handling
* View Binding integration

**Features:**

* Text input for user name
* “Say Hello” button
* IME action handling (DONE)
* Input validation (trim)
* Keyboard management
* State persistence on rotation (`onSaveInstanceState`)

---

### 🧮 E02 — Calculator

A structured calculator application with proper architecture.

**Architecture:**

* Activity → UI layer only
* ViewModel → business logic

**Technologies:**

* View Binding
* ViewModel
* LiveData
* BigDecimal (precision-safe arithmetic)

**Features:**

* Basic operations (+ − × ÷)
* Decimal support
* Percentage calculation
* Sign toggle (+/−)
* Division-by-zero handling
* State persistence across rotation
* Grid-based UI (GridLayout)



---

### 📱 E03+ — Advanced Application Exercises

Subsequent exercises extend the same principles and introduce:

* More complex UI layouts
* Multi-component interaction
* Improved state management
* Architectural scalability
* Real-world app design patterns

Each project is structured as a **complete Android application**, not a partial implementation.

---

## ⚙️ Technologies Used

* **Kotlin**
* **Android SDK**
* **Android Studio**
* **Gradle (Kotlin DSL)**
* **View Binding**
* **ViewModel & LiveData**

---

## 💻 Requirements

To build and run the projects:

### Software

* Android Studio (latest stable)
* JDK 17+
* Gradle (wrapper included)

### Hardware

* Minimum 8 GB RAM (16 GB recommended)
* Emulator or physical Android device (API 26+)



---

## 🚀 How to Build (Terminal)

Each exercise must be built independently.

### 1. Navigate to the project

```bash
cd textbook/E02_Calculator
```

### 2. Clean project

```bash
./gradlew clean
```

### 3. Build APK

```bash
./gradlew assembleDebug
```

### 4. Run tests (optional)

```bash
./gradlew test
```

---

## ▶️ How to Run (Android Studio)

1. Open Android Studio
2. Click:

```
File → Open
```

3. Select:

```
textbook/E0X_ProjectName
```

4. Wait for Gradle sync
5. Run on emulator/device

---

## 🧪 Testing Strategy

Each project is tested for:

* User input correctness
* Button interactions
* UI behavior
* Screen rotation handling
* Error cases (e.g., invalid input)



---

## ⚠️ Common Errors & Fixes

### ❌ “Does not contain a Gradle build”

Cause:

* Opening `textbook/` instead of a project

Fix:

```
Open textbook/E0X_ProjectName
```

---

### ❌ ViewBinding not found

Fix:

```kotlin
buildFeatures {
    viewBinding = true
}
```

---

### ❌ GridLayout crash

Use:

```xml
<android.widget.GridLayout>
```

NOT:

```xml
androidx.constraintlayout.widget.GridLayout
```

---

### ❌ AppCompatActivity errors

Fix:
Use:

```kotlin
ComponentActivity
```

---

## 🧠 Learning Outcomes

By completing all exercises, the student demonstrates:

* Ability to build Android apps using Kotlin
* Understanding of Android architecture patterns
* Proper separation between UI and logic
* Handling of lifecycle and state
* Writing maintainable and testable code

---

## 👨‍💻 Author

Simone Perrotta
Università degli Studi di Napoli “Parthenope”

---

## 📌 Final Note

These exercises represent a **progressive and structured learning path**, aligned with the official course textbook and designed to reflect **real-world Android development practices**.
