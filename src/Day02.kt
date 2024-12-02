import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safeCount = 0
        val safeDeltas = listOf(1, 2, 3)
        for (line in input) {
            val levels = line.split(" ").map { it.toInt() }
            var isSafe = true
            var direction = levels.first() - levels.last()  //increasing or decreasing?

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
            } else {
                isSafe = true
            }
        }
        return safeCount
    }

    fun part2(input: List<String>): Int {
        var safeCount = 0
        for (line in input) {
            val safePDeltas = mutableSetOf(1, 2, 3)
            val safeNDeltas =  mutableSetOf(-1,-2,-3)
            val levels = line.split(" ").map { it.toInt() }
            for (levelIdx in 1 until levels.size) {
                val levelDiff = levels[levelIdx] - levels[levelIdx - 1]
                safePDeltas.add(levelDiff)
                safeNDeltas.add(levelDiff)
            }

            if (safePDeltas.size == 4 || safeNDeltas.size == 4) {
                safeCount++
            }

        }
        return safeCount
    }

    fun parseInputToDataStructure(input: List<String>): List<Int> {

        return listOf(0,1,2)
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
