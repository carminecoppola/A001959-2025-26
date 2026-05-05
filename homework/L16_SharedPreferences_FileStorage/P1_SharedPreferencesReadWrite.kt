// L16 - P1: read and write with SharedPreferences.
// This example shows the basic idea: save a key/value pair and then read it.

class SharedPreferencesSimulatorP1 {
    private val storage = mutableMapOf<String, String>()

    // Salviamo a valore di text in memoria.
    fun putString(key: String, value: String) {
        storage[key] = value
    }

    // We read a text value, or return a default value.
    fun getString(key: String, defaultValue: String = ""): String {
        return storage[key] ?: defaultValue
    }

    // This method shows the full write/read flow.
    fun saveAndReadExample(): String {
        putString("username", "student")
        return getString("username")
    }
}

// Basic use case: we simulate saving a username.
fun demoL16P1SharedPreferencesReadWrite(): String {
    return SharedPreferencesSimulatorP1().saveAndReadExample()
}

fun main() {
    println("=== SharedPreferences Read/Write ===")
    println(demoL16P1SharedPreferencesReadWrite())
}