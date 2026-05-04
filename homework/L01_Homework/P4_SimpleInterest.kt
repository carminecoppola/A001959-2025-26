/*
Problem 4 - Simple Interest

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P4_SimpleInterest.kt -include-runtime -d P4_SimpleInterest.jar
2. Run the program:
   java -jar P4_SimpleInterest.jar
*/

fun main() {
    print("Principal: "); val p = readLine()!!.toDouble()
    print("Rate (%): ");  val r = readLine()!!.toDouble()
    print("Years: ");     val t = readLine()!!.toDouble()
    val si = (p * r * t) / 100
    println("Simple Interest: ${String.format("%.2f", si)}")
    println("Total Amount:    ${String.format("%.2f", p + si)}")
}
