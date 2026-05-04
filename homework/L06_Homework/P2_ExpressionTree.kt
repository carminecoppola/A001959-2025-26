/*
Problem 2 - Expression Tree

Goal:
- Complete this Kotlin exercise implementation.

Notes:
- Documentation translated to English.
- Program logic remains unchanged.

How to run from terminal:
1. Compile the file:
   kotlinc P2_ExpressionTree.kt -include-runtime -d P2_ExpressionTree.jar
2. Run the program:
   java -jar P2_ExpressionTree.jar
*/

sealed class Expr {
    data class Num(val value: Int) : Expr()
    data class Add(val l: Expr, val r: Expr) : Expr()
    data class Mul(val l: Expr, val r: Expr) : Expr()
}

fun evaluate(e: Expr): Int = when(e) {
    is Expr.Num -> e.value
    is Expr.Add -> evaluate(e.l) + evaluate(e.r)
    is Expr.Mul -> evaluate(e.l) * evaluate(e.r)
}

fun main() {
    val expr = Expr.Mul(Expr.Add(Expr.Num(3), Expr.Num(3)), Expr.Num(4))
    println(evaluate(expr))  // (3+3)*4 = 24
}
