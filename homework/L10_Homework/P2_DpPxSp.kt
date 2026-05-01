/*
Problem 2 - dp vs px vs sp

Obiettivo:
- Comprendere le differenze tra dp, px e sp.
- Scegliere l'unita corretta per dimensioni, margini, padding e testo.

Spiegazione:
- `dp` significa density-independent pixels ed è usato per dimensioni visive come margin e padding.
- `sp` è simile a dp ma rispetta le impostazioni di accessibilità per il testo.
- `px` sono pixel reali e dipendono dalla densità del device, quindi sono da evitare nella UI Android.
- Formula utile: `px = dp × (dpi / 160)`.
- In pratica: usa `dp` per layout e `sp` per il font size.

Micro-esempio XML:
android:layout_margin="16dp"
android:textSize="18sp"

Esempio concettuale:
- 16dp resta proporzionato su schermi diversi.
- 18sp cresce o diminuisce con le preferenze di font scaling.
- 16px, invece, appare incoerente tra dispositivi con densità diverse.

Edge cases / common mistakes:
- Usare `px` per margin o text size rende la UI non responsiva.
- Usare `dp` per il testo ignora il font scaling dell'utente.
- Confondere densità e dimensione reale può creare layout troppo piccoli o troppo grandi.
- Su device ad alta densità, pochi px possono risultare quasi invisibili.

Come eseguirlo da terminale:
1. Compila il file:
    kotlinc P2_DpPxSp.kt -include-runtime -d P2_DpPxSp.jar
2. Esegui il programma:
    java -jar P2_DpPxSp.jar
3. Questo esercizio non richiede input interattivo.

*/

fun main() {
    println("dp vs px vs sp — solution")
}
