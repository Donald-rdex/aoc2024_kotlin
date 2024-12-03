fun main() {
    fun part1(input: List<String>): Int {
        val multiplyPattern = "mul\\((?<a>[0-9]+),(?<b>[0-9]+)\\)".toRegex()

        var mulSum = 0
        for (line in input) {
            val startRe = 0
            multiplyPattern.findAll(line, startRe).forEach { match ->
                mulSum += (match.groups["a"]?.value?.toInt() ?: 0) * (match.groups["b"]?.value?.toInt() ?: 0)
            }
        }

        return mulSum
    }

    fun part2(input: List<String>): Int {
        val mulOrEnable = """(mul\((?<a>[0-9]+),(?<b>[0-9]+)\)|do\(\)|don't\(\))""".toRegex()
        var mulSum = 0

        var enabled = true

        input.forEach { line ->
            mulOrEnable.findAll(line).forEach { match ->
                when (match.value) {
                    "don't()" -> enabled = false
                    "do()" -> enabled = true
                    else -> if (enabled) {
                        mulSum += (match.groups["a"]?.value?.toInt() ?: 0) * (match.groups["b"]?.value?.toInt() ?: 0)
                    }
                }
//                println("$enabled: ${match.value}")  // debugging state changes
            }
        }
        return mulSum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()

    check(part2(testInput) == 48)
    part2(input).println()
}
