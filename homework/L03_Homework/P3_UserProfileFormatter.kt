/*
Problem 3 - User Profile Formatter
Obiettivo:
- Creare una funzione che formatta i dati di un utente.
- Sostituire i valori null con "N/A".

Spiegazione codice:
- La data class User modella i dati del profilo con bio e website nullable.
- La funzione format usa l'Elvis operator ?: per sostituire i null con "N/A".
- Il risultato è una stringa leggibile con nome, bio e sito web.
- Nel main si mostra un esempio con bio nulla.

Edge cases:
- bio null: viene stampato "N/A".
- website null: viene stampato "N/A".
- name non è nullable, quindi viene sempre stampato come valore presente.

Come compilare ed eseguire:
1- Compila il file
    kotlinc P3_UserProfileFormatter.kt -include-runtime -d P3_UserProfileFormatter.jar
2- Esegui il programma
    java -jar P3_UserProfileFormatter.jar
3- Questo esercizio non richiede input
*/

data class User(val name: String, val bio: String?, val website: String?)

fun format(user: User): String =
    "Name: ${user.name}\nBio: ${user.bio ?: "N/A"}\nWebsite: ${user.website ?: "N/A"}"

fun main() {
    println(format(User("Alice", null, "https://alice.dev")))
}