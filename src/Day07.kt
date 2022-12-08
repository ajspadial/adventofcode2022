abstract class DirectoryElement(val name: String) {
    abstract fun getSize(): Int
}

class Directory(name: String, val parent: Directory?) : DirectoryElement(name) {
    val elements = mutableListOf<DirectoryElement>()

    override fun getSize(): Int {
        return elements.sumOf { it.getSize()}
    }
}
class File(name: String, private val size: Int): DirectoryElement(name) {
    override fun getSize(): Int {
        return size
    }
}
fun main() {



    fun findSmaller(root: Directory, limitSize: Int, list: MutableList<Directory>) {
        if (root.getSize()<=limitSize) {
            list.add(root)
        }
        for (e in root.elements) {
            if (e is Directory) {
                findSmaller(e, limitSize, list)
            }
        }
    }

    fun findBigger(root: Directory, limitSize: Int, list: MutableList<Directory>) {
        val s = root.getSize()
        if (s >=limitSize) {
            list.add(root)
        }
        for (e in root.elements) {
            if (e is Directory) {
                findBigger(e, limitSize, list)
            }
        }
    }

    fun loadDirectory(input: List<String>): Directory {
        var ls = false
        val rootDirectory = Directory("/", null)
        var currentDirectory = rootDirectory
        for (line in input) {
            if (line.startsWith("$ cd")) {
                ls = false
                val dirname = line.substring(5)
                if (dirname == "/") {
                    currentDirectory = rootDirectory
                } else if (dirname == "..") {
                    currentDirectory = currentDirectory.parent!!
                } else {
                    for (d in currentDirectory.elements) {
                        if (d.name == dirname && d is Directory) {
                            currentDirectory = d
                            break
                        }
                    }
                }
            }
            else if (line.startsWith("$ ls")) {
                ls = true
            }
            else if (ls) {
                val elements = line.split(" ")
                val name = elements[1]
                if (elements[0] == "dir") {
                    currentDirectory.elements.add(Directory(name, currentDirectory))
                }
                else {
                    val size = elements[0].toInt()
                    currentDirectory.elements.add(File(name, size))
                }
            }
        }

        return rootDirectory
    }


   fun part1(input: List<String>): Int {

       val rootDirectory = loadDirectory(input)

       val candidates = mutableListOf<Directory>()

       findSmaller(rootDirectory, 100000, candidates)

       var sum = 0
       for (d in candidates) {
           sum += d.getSize()
       }

       return sum
   }

    fun part2(input: List<String>): Int {

        val rootDirectory = loadDirectory(input)

        val unusedSpace = 70000000 - rootDirectory.getSize()
        val neededSpace = 30000000 - unusedSpace

        val candidates = mutableListOf<Directory>()

        findBigger(rootDirectory, neededSpace, candidates)

        var result = candidates[0].getSize()
        for (c in candidates) {
            val s = c.getSize()

            if (s < result) {
                result = s
            }
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
