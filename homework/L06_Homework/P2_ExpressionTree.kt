/*
Problem 2 - Expression Tree
Obiettivo:
- Creare sealed class Expr con Num, Add, Mul e funzione evaluate(expr).

Spiegazione codice:
- Expr è sealed per permettere l'uso esaustivo di when.
- Le classi interne modellano i nodi dell'albero di espressione.
- La funzione evaluate usa ricorsione per calcolare il valore dell'espressione.

Edge cases:
- L'uso di sealed class garantisce che when sia esaustivo, evitando missing branch.
- Valori e operazioni sono interi, overflow non gestito esplicitamente.

Come eseguirlo da terminale:
1- Compila il file
    kotlinc P2_ExpressionTree.kt -include-runtime -d P2_ExpressionTree.jar
2- Esegui il programma
    java -jar P2_ExpressionTree.jar
3- Questo esercizio non richiede input
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
