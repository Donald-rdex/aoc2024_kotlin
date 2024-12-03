import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safeCount = 0
        val safeDeltas = listOf(1, 2, 3)
        for (line in input) {
            val levels = line.split(" ").map { it.toInt() }
            var isSafe = true
            val direction = levels.first() - levels.last()  //increasing or decreasing?

            for (levelIdx in 0 until levels.size - 1) {
                if (direction >= 0) {
                    if (abs(levels[levelIdx] - levels[levelIdx + 1]) !in safeDeltas) {
                        isSafe = false
                    }
                    if (levels[levelIdx] < levels[levelIdx + 1]) {
                        isSafe = false
                    }

                } else {
                    if ((levels[levelIdx + 1] - levels[levelIdx]) !in safeDeltas) {
                        isSafe = false
                    }
                    if (levels[levelIdx] > levels[levelIdx + 1]) {
                        isSafe = false
                    }
                }
            }

            if (isSafe) {
                safeCount++
            }
        }
        return safeCount
    }

    fun part1Alternate(input: List<String>): Pair<Int, MutableList<List<Int>>> {
        // alternate solution to part 1 using sets.
        var safeCount = 0
        val unsafeLists = mutableListOf<List<Int>>()
        for (line in input) {
            val safePDeltas = mutableSetOf(1, 2, 3)
            val safeNDeltas =  mutableSetOf(-1,-2,-3)
            val levels = line.split(" ").map { it.toInt() }
            for (levelIdx in 1 until levels.size) {
                val levelDiff = levels[levelIdx] - levels[levelIdx - 1]
                safePDeltas.add(levelDiff)
                safeNDeltas.add(levelDiff)
            }

            if (safePDeltas.size == 3 || safeNDeltas.size == 3) {
                safeCount++
            } else {
                unsafeLists.add(levels)
            }
        }

        return Pair(safeCount, unsafeLists)
    }

    fun part2(input: List<String>): Int {
        var (safeCount, unsafeLists) = part1Alternate(input)

        for (unsafeList in unsafeLists) {
//            println(unsafeList)
            var workingMutations = 0
            for (levelIdx in unsafeList.indices) {
                //remove element and see if remaining list succeeds.
                var unsafeString = ""
                unsafeList.forEachIndexed { idx, iit ->
                    if (idx != levelIdx) { unsafeString += "$iit " }
                }
//                println(listOf(unsafeString.trim()))
                if (part1(listOf(unsafeString.trim())) > 0 ) {
                    workingMutations++
//                    println(workingMutations)
                }
                if (workingMutations > 1) { break }
            }
            if (workingMutations >= 1) {
//                println("safe!")
                safeCount++
            }
        }
//        println(safeCount)
        return safeCount
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()

    check(part2(testInput) == 4)
    part2(input).println()
}
