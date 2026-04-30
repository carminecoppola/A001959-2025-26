/*
Problem 5 - Password Validator
Obiettivo:
Verificare se una password:
- ha almeno 8 caratteri
- contiene almeno un numero
- contiene almeno una lettera maiuscola
Stampare VALID o INVALID.

Spiegazione codice:
- Si legge la password; se null si usa stringa vuota.
- Si calcolano tre condizioni booleane indipendenti.
- Si stampano i risultati dei controlli.
- La password è valida solo se tutte le condizioni sono vere.

Edge cases:
- Password vuota o null risulta INVALID.
- Password con soli simboli senza cifra/maiuscola fallisce i controlli.
 
Come eseguirlo da terminale:
1- Compila il file
    kotlinc P5_PasswordValidator.kt -include-runtime -d P5_PasswordValidator.jar
2- Esegui il programma
    java -jar P5_PasswordValidator.jar
3- Inserisci la password quando richiesto a schermo.

*/
fun main() {
    print("Password: ")
    val pwd = readLine() ?: ""
    val long  = pwd.length >= 8
    val digit = pwd.any { it.isDigit() }
    val upper = pwd.any { it.isUpperCase() }
    println("At least 8 chars: $long")
    println("Contains digit:   $digit")
    println("Contains upper:   $upper")
    println(if (long && digit && upper) "VALID" else "INVALID")
}
