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
        val startPattern = "(?<beginning>.*)don't\\(\\)".toRegex()
        val enablePattern = "do\\(\\)(?<enabled>.*)don't\\(\\)".toRegex()
        val endPattern = "do\\(\\)(?<enabled>.*)$".toRegex()
        var mulSum = 0

        input.forEach { line ->
            var startRe = 1
            // lines start enabled, so only have to find where they stop
            startPattern.find(line, startRe)?.let { match ->
                startRe += match.groups["beginning"]?.range?.last?.plus(0) ?: 0
                mulSum += part1(listOf("", match.groups["beginning"].toString()))
            }
            enablePattern.findAll(line, startRe).let { match ->
                match.forEach { innerMatch ->
                    startRe += innerMatch.groups["enabled"]?.range?.last?.plus(0) ?: 0
                    mulSum += part1(listOf(innerMatch.groups["enabled"]?.value.toString()))
                }
            }
            endPattern.find(line, startRe)?.let { match ->
                mulSum += part1(listOf("", match.groups["enabled"].toString()))
            }

        }
        println("returning: $mulSum")
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
