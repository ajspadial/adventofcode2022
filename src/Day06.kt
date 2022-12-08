fun main() {

    fun areDistinctCharacters(s: String): Boolean {
        var distinct = true

        for (i in 0 until s.length) {
            if (s[i] in s.substring(i + 1)) {
                distinct = false
                break
            }
        }

        return distinct
    }

    fun findFirstDistinctPackage(input: String, size: Int): Int {
        var i = 0
        var found = false

        while (!found && i < input.length - size + 1) {
            val packet = input.substring(i, i + size)
            found = areDistinctCharacters(packet)

            i++
        }

        return i + size - 1
    }


   fun part1(input: String): Int {

       return findFirstDistinctPackage(input, 4)
   }

    fun part2(input: String): Int {
        return findFirstDistinctPackage(input, 14)
    }

    // test if implementation meets criteria from the description, like:

    check(part1("bvwbjplbgvbhsrlpgdmjqwftvncz") == 5)
    check(part1("nppdvjthqldpwncqszvftbrmjlhg") == 6)
    check(part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 10)
    check(part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 11)
    check(part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
    check(part2("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
    check(part2("nppdvjthqldpwncqszvftbrmjlhg") == 23)
    check(part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
    check(part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

    val input = readInput("Day06")
    println(part1(input[0]))
    println(part2(input[0]))
}
