fun main() {

    fun calcPriority (c: Char): Int {
        return if (c in 'a'..'z')
            c.code - 'a'.code + 1
        else
            c.code - 'A'.code + 27
    }

   fun part1(input: List<String>): Int {

    var score = 0
       for (line in input) {
           val firstCompartment = line.substring(0..line.length/2 - 1)
           val secondCompartment = line.substring(line.length/2)

           for (element in firstCompartment) {
               if (element in secondCompartment) {
                   score += calcPriority(element)
                   break
               }
           }
       }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0

        for (i in 0..input.size / 3 - 1) {
            val rucksack0 = input[i * 3]
            val rucksack1 = input[i * 3 + 1]
            val rucksack2 = input[i * 3 + 2]

            for (element in rucksack0) {
                if (element in rucksack1 && element in rucksack2) {
                    score += calcPriority(element)
                    break
                }
            }
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
