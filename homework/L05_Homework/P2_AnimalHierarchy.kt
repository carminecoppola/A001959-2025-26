/*
Problem 2 - Animal Hierarchy

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_AnimalHierarchy.kt -include-runtime -d P2_AnimalHierarchy.jar
2. Run the program:
   java -jar P2_AnimalHierarchy.jar
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
