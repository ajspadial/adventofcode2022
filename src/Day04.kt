fun main() {

    fun getRange(rangeString: String): IntRange {
        val values = rangeString.split("-")
        val min = values[0].toInt()
        val max = values[1].toInt()

        return min..max
    }


   fun part1(input: List<String>): Int {

        var score = 0

       for (lines in input) {
           val pair = lines.split(",")

           val range1 = getRange(pair[0])
           val range2 = getRange(pair[1])

           if (range1.first in range2 && range1.last in range2 || range2.first in range1 && range2.last in range1) {
               score++
           }
       }


        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0

        for (lines in input) {
            val pair = lines.split(",")

            val range1 = getRange(pair[0])
            val range2 = getRange(pair[1])

            if (range1.first in range2 || range1.last in range2 || range2.first in range1 || range2.last in range1) {
                score++
            }
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
