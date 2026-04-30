/*
Problem 1 - Stack Implementation
Obiettivo:
- Implementare una classe generica Stack<T> con push, pop, peek, isEmpty e size.

Spiegazione codice:
- La classe Stack<T> utilizza una MutableList interna per memorizzare gli elementi.
- push aggiunge un elemento in coda, pop rimuove e ritorna l'ultimo elemento o null se vuota.
- peek ritorna l'ultimo elemento senza rimuoverlo usando lastOrNull().
- isEmpty e la proprietà size forniscono informazioni sullo stato della struttura.
- toString override fornisce una rappresentazione utile per il debug.

Edge cases:
- pop su stack vuoto restituisce null invece di lanciare un'eccezione.
- peek su stack vuoto restituisce null.
- La classe è generica e funziona con qualsiasi tipo T.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P1_StackImplementation.kt -include-runtime -d P1_StackImplementation.jar
2- Esegui il programma
    java -jar P1_StackImplementation.jar
3- Questo esercizio non richiede input
*/

class Stack<T> {
    private val items = mutableListOf<T>()

    fun push(item: T) = items.add(item)
    fun pop(): T? = if (isEmpty()) null else items.removeAt(items.size - 1)
    fun peek(): T? = items.lastOrNull()
    fun isEmpty() = items.isEmpty()

    val size get() = items.size

    override fun toString() = items.toString()
}

fun main() {
    val s = Stack<Int>()
    s.push(1); s.push(2); s.push(3)

    println(s.peek())  // 3
    println(s.pop())   // 3
    println(s)
}
