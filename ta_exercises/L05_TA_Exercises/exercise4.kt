

// Exercise 4 - Interface - Define a Drawable interface and implement it in multiple classes.

package l05.exercise4

// Le INTERFACCE in Kotlin definiscono un contratto che le classi possono implementare.
// A differenza di Java 7, in Kotlin (e Java 8+) le interfacce possono avere
// metodi con implementazione di default — non solo metodi astratti.
interface Drawable {

    // Metodo astratto: deve essere implementato da chi implementa l'interfaccia
    fun draw()  

    // Metodo con implementazione di default: può essere usato così com'è
    // oppure sovrascritto dalla classe implementante
    fun resize(factor: Double) { println("Resizing by $factor") }
}

class Icon(val name: String) : Drawable {
    // 'override' obbligatorio per i metodi astratti dell'interfaccia
    override fun draw() = println("Drawing icon: $name")
    // 'resize' non viene sovrascritto: usa l'implementazione di default
}

fun main() {
    val icons = listOf(Icon("home"), Icon("search"))

    // Per ogni icona chiamiamo draw() e poi resize()
    // Il ';' permette di mettere più istruzioni sulla stessa riga (sconsigliato
    // in produzione, ma accettabile per esempi brevi)
    icons.forEach { it.draw(); it.resize(2.0) }
    // Output: Drawing icon: home
    //         Resizing by 2.0
    //         Drawing icon: search
    //         Resizing by 2.0

    // 💡 Una classe Kotlin può implementare più interfacce contemporaneamente,
    //    ma può estendere una sola superclasse (ereditarietà singola).
    //    Esempio: class Button : Drawable, Clickable, Focusable { ... }
}