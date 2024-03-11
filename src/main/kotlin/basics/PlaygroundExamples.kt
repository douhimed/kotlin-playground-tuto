package basics

fun main() {

    stringExample();

    whenExemple(1)
    whenExemple(2)
    whenExemple(10)

    rangeAndLoopsExamples()

    functionsDemo()

    rangeAndLoopsExamples()

    println(describe("Hello"))

    automaticCastExampleV1("Med")
    println(automaticCastExampleV2("Med"))
    println(automaticCastExampleV2(""))

    classExamples()

}

private fun automaticCastExampleV1(s: Any) {
    /**
     * without return => s is of type Any
     * with return s is of type String (like example of instance of in java when throws error)
     */
    if(s !is String) {
        println("Not a string")
        return
    }

    println("s length : ${s.length}")
}

private fun automaticCastExampleV2(s: Any) : Int? {
    if(s is String && s.length > 0) {
        s.length
    }

    return null
}

/**
 * function returns something, we can remove { } and use = directly (like in JS)
 */
fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

val PI = 3.14

open class Shape {}

class Circle(r: Double) : Shape() {
    val rayon = r
    val surface = PI * Math.pow(rayon, 2.0)
    val perimetre = 2 * rayon * PI
}


fun classExamples() {
    val circle = Circle(10.0)

    val infos = """
        rayon = ${circle.rayon}
        surface = ${circle.surface}
        perimetre = ${circle.perimetre}
    """.trimIndent()

    println(infos)
}

private fun functionsDemo() {

    val a = 10.0
    val b = 2.0

    println("${OPERATION.SUB} ${applyOperation(a, b, OPERATION.SUB)}")
    println("${OPERATION.SUM} ${applyOperation(a, b, OPERATION.SUM)}")
    println("${OPERATION.DIV} ${applyOperation(a, b, OPERATION.DIV)}")
    println("${OPERATION.MUL} ${applyOperation(a, b, OPERATION.MUL)}")
}

enum class OPERATION {
    SUM, DIV, SUB, MUL
}

private fun applyOperation(a: Double, b: Double, op: OPERATION): Double {
    return when (op) {
        OPERATION.SUM -> a + b
        OPERATION.SUB -> a - b
        OPERATION.DIV -> a / b
        OPERATION.MUL -> a * b
    }
}

private fun rangeAndLoopsExamples() {

    val range = 1..10
    for (i in range) println("i : $i")

    val rangeDown = 10 downTo 1
    for (i in rangeDown) println("using downTo : i : $i")

    for (i in rangeDown step 2) println("Using steps 2 i $i")

}

private fun stringExample() {

    val finalString = "This final String (use val)"
    println(finalString)

    var name = "Med"
    println("String interpolation (name is $name) has length of ${name.length}")
}

private fun whenExemple(level: Int) {

    val levelStatus = when (level) {
        0 -> "HIGHT"
        1 -> "MEDIUM"
        2 -> "LOW"
        else -> "IGNORED"
    }

    println("levelStatus : $levelStatus")
}