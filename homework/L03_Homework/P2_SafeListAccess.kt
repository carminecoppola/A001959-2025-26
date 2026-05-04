/*
Problem 2 - Safe List Access

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_SafeListAccess.kt -include-runtime -d P2_SafeListAccess.jar
2. Run the program:
   java -jar P2_SafeListAccess.jar
*/

fun safeGet(list: List<Int>, index: Int): Int? = list.getOrNull(index)

fun main() {
    val nums = listOf(10, 20, 30)
    println(safeGet(nums, 1))   // 20
    println(safeGet(nums, 99))  // null
}