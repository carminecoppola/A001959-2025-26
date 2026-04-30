/*
Problem 2 - Animal Hierarchy
Obiettivo:
- Creare una gerarchia Animal con sottoclassi Dog e Cat, ciascuna con un metodo specifico.

Spiegazione codice:
- Animal è open e contiene proprietà condivise come name e sound.
- Dog e Cat ereditano da Animal; Dog aggiunge fetch, Cat aggiunge purr.
- speak è open e stampa il nome e il suono dell'animale.

Edge cases:
- L'ereditarietà richiede la parola chiave open sulla classe base.
- Il cast diretto (animals[0] as Dog) presuppone che l'elemento sia effettivamente un Dog.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P2_AnimalHierarchy.kt -include-runtime -d P2_AnimalHierarchy.jar
2- Esegui il programma
    java -jar P2_AnimalHierarchy.jar
3- Questo esercizio non richiede input
*/


open class Animal(val name: String, val sound: String) {
    open fun speak() = println("$name says $sound")
}

class Dog(name: String) : Animal(name, "Woof") {
    fun fetch() = println("$name fetches the ball!")
}

class Cat(name: String) : Animal(name, "Meow") {
    fun purr() = println("Purrrr...")
}

fun main() {
    val animals = listOf(Dog("Rex"), Cat("Whiskers"))
    animals.forEach { it.speak() }

    (animals[0] as Dog).fetch()
    (animals[1] as Cat).purr()

}
