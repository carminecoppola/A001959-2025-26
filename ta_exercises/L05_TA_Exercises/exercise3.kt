

// Exercise 3 - Inheritance - Create a Shape hierarchy with area() override.

package l05.exercise3

// In Kotlin le classi sono 'final' (non estendibili) per default.
// Per renderle estendibili occorre dichiararle con la parola chiave 'open'.
// Questo è il contrario di Java, dove tutte le classi sono estendibili di default.
open class Shape(val color: String) {

    // Anche i metodi devono essere 'open' per poter essere sovrascritti
    open fun area() = 0.0

    // Questo metodo NON è 'open': le sottoclassi non possono sovrascriverlo.
    // Nota: chiama area() che è polimorfico → verrà chiamata la versione
    // della sottoclasse concreta a runtime (polimorfismo dinamico)
    fun describe() = "$color shape, area=${area()}"
}

// ':' indica l'ereditarietà. 'Shape(color)' invoca il costruttore della superclasse.
class Circle(color: String, val radius: Double) : Shape(color) {
    // 'override' è obbligatorio in Kotlin (non opzionale come in Java con @Override)
    override fun area() = Math.PI * radius * radius
}

class Rect(color: String, val w: Double, val h: Double) : Shape(color) {
    override fun area() = w * h
}

fun main() {
    // La lista è di tipo List<Shape>: polimorfismo in azione
    val shapes = listOf(Circle("red", 5.0), Rect("blue", 4.0, 3.0))

    // 'forEach' itera la lista; per ogni elemento chiama describe()
    // che a sua volta chiama area() della sottoclasse corretta
    shapes.forEach { println(it.describe()) }
    // Output: red shape, area=78.53981633974483
    //         blue shape, area=12.0
}