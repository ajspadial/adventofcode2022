
data class Elf(var sum: Int = 0) {

}
fun main() {

    fun calculateCalories(input: List<String>): List<Elf> {
        val elfs: MutableList<Elf> = mutableListOf()
        var currentElf = Elf()
        elfs.add(currentElf)

        for (line in input) {
            if (line == "") {
                currentElf = Elf()
                elfs.add(currentElf)
            }
            else {
                val calories = line.toInt()
                currentElf.sum += calories
            }
        }
        return elfs
    }

    fun part1(input: List<String>): Int {

        val elfs = calculateCalories(input)

        var maxCalories = 0
        for (elf in elfs) {
            if (elf.sum > maxCalories) maxCalories = elf.sum
        }

        return maxCalories
    }

    fun part2(input: List<String>): Int {
        val elfs = calculateCalories(input)

        val top3 = mutableListOf(0,0,0)

        for (elf in elfs) {
            if (elf.sum > top3[0]) {
                top3[2] = top3[1]
                top3[1] = top3[0]
                top3[0] = elf.sum
            }
            else if (elf.sum > top3[1]) {
                top3[2] = top3[1]
                top3[1] = elf.sum
            }
            else if (elf.sum > top3[2]) {
                top3[2] = elf.sum
            }
        }

        return top3.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
