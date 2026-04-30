

// Exercise 1 - Hello, World!

package l01.exercise1

fun main() {
    // 'val' dichiara una variabile immutabile (non può essere riassegnata)
    // Il tipo String viene inferito automaticamente dal compilatore
    val name = "Pippo" 

    // Le String Template permettono di incorporare variabili direttamente
    // nella stringa usando il simbolo '$' seguito dal nome della variabile
    println("Hello, $name!") 
}

/*
    ******Output******
    Hello, Pippo!
*/