/*
Problem 1 - Stack Implementation

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P1_StackImplementation.kt -include-runtime -d P1_StackImplementation.jar
2. Run the program:
   java -jar P1_StackImplementation.jar
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
