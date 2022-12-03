
fun main() {

    fun scoreShape(shape: String): Int {
        return when (shape) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
    }

    fun scoreRound(opponent: String, response: String): Int {
        if (opponent == "A") {
            return when (response) {
                "X" -> 3
                "Y" -> 6
                "Z" -> 0
                else -> 0
            }
        }
        if (opponent == "B") {
            return when (response) {
                "X" -> 0
                "Y" -> 3
                "Z" -> 6
                else -> 0
            }
        }
        if (opponent == "C") {
            return when (response) {
                "X" -> 6
                "Y" -> 0
                "Z" -> 3
                else -> 0
            }
        }
        return 0
    }

    fun response(opponent: String, result: String):String {
        if (opponent == "A") {
            return when (result) {
                "X" -> "Z"
                "Y" -> "X"
                "Z" -> "Y"
                else -> ""
            }
        }
        if (opponent == "B") {
            return when (result) {
                "X" -> "X"
                "Y" -> "Y"
                "Z" -> "Z"
                else -> ""
            }
        }
        if (opponent == "C") {
            return when (result) {
                "X" -> "Y"
                "Y" -> "Z"
                "Z" -> "X"
                else -> ""
            }
        }
        return ""
    }

    fun part1(input: List<String>): Int {

        var score = 0
        for (line in input) {
            val symbols = line.split(" ")
            score += scoreShape(symbols[1]) + scoreRound(symbols[0], symbols[1])
        }

       return score
    }

    fun part2(input: List<String>): Int {

        var score = 0
        for (line in input) {
            val symbols = line.split(" ")
            val response = response(symbols[0], symbols[1])
            score += scoreShape(response) + scoreRound(symbols[0], response)
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
