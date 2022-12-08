import java.util.*

fun main() {

    fun loadCrates(input: List<String>): MutableList<Stack<String>> {
        val stacks : MutableList<Stack<String>> = mutableListOf<Stack<String>>()

        for (line in input) {
            var nextCrate = line.indexOf('[')
            while (nextCrate != -1) {
                val crate = line.substring(nextCrate + 1, nextCrate + 2)
                val stackNumber = nextCrate / 4 + 1
                if (stackNumber > stacks.size) {
                    val currentStackSize = stacks.size
                    for (i in currentStackSize until stackNumber) {
                        stacks.add(Stack())
                    }
                }
                stacks[stackNumber - 1].push(crate)
                nextCrate = line.indexOf("[", nextCrate + 1)
            }
        }

        stacks.forEach{it.reverse()}

        return stacks
    }

   fun part1(input: List<String>): String {

        var result = ""

       val stacks = loadCrates(input)

       for (line in input) {
           if (line.startsWith("move")) {
               // then process orders
               val quantity = line.substring(5, line.indexOf("from") - 1).toInt()
               val origin = line.substring(line.indexOf("from") + 5, line.indexOf("to") - 1).toInt()
               val destination = line.substring(line.indexOf("to") + 3).toInt()

               for (i in 1..quantity) {
                   val crate = stacks[origin - 1].pop()
                   stacks[destination - 1].push(crate)
               }
           }
       }

       for (s in stacks) {
           result += s.pop()
       }

       return result
   }

    fun part2(input: List<String>): String {
        var result = ""

        val stacks = loadCrates(input)


        for (line in input) {
            if (line.startsWith("move")) {
                // then process orders
                val quantity = line.substring(5, line.indexOf("from") - 1).toInt()
                val origin = line.substring(line.indexOf("from")+5, line.indexOf("to") - 1).toInt()
                val destination = line.substring(line.indexOf("to")+3).toInt()

                val intermediateStack = Stack<String>()
                for (i in 1 .. quantity) {
                    val crate = stacks[origin - 1].pop()
                    intermediateStack.push(crate)
                }
                for (i in 1 .. quantity) {
                    val crate = intermediateStack.pop()
                    stacks[destination - 1].push(crate)
                }


            }
        }

        for (s in stacks) {
            result += s.pop()
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
