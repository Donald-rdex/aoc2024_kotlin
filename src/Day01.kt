import kotlin.math.abs

fun main() {
    fun part1(listA: List<Int>, listB: List<Int> ): Int {
        var listSum = 0
        for (i in listA.indices) {
            val listDiff = abs(listA[i] - listB[i])
            listSum += listDiff
        }

        return listSum
    }

    fun part2(listA: List<Int>, listB: List<Int>): Int {
        var listSum = 0
        for (i in listA.indices) {
            val occurrence = listB.count { it == listA[i] }
            listSum += occurrence * listA[i]
        }

        return listSum
    }

    fun inputToGroups(input: List<String>) : Pair<MutableList<Int>, MutableList<Int>> {
        val groupOne = mutableListOf<Int>()
        val groupTwo = mutableListOf<Int>()
        input.forEach { it ->
            val (a, b) = it.split("   ").map { it.toInt() }
            groupOne.add(a)
            groupTwo.add(b)
        }
        // Needed for part one for sure, not sure if will be needed for part two.
        groupOne.sort()
        groupTwo.sort()

        return Pair(groupOne, groupTwo)
    }

    fun alternateSolutions(input: List<String>) : Int {
        // since in part two we have to compare ListA against a count of number of times it shows up in the B List,
        // make a map of B to the count of number of times a number shows up in the list.
        //
        var partTwo = 0
        val bCounter = mutableMapOf<Int, Int>()
        val aList = mutableListOf<Int>()
        input.forEach { line ->
            val (a, b) = line.split("   ").map { it.toInt() }
            aList.add(a)
            val oldBCount = bCounter.putIfAbsent(b, 1)
            if (oldBCount != null) {
                bCounter[b] = oldBCount + 1
            }
        }
        // still an n^2 solution though :-(
        aList.forEach { it -> partTwo += it * bCounter.getOrDefault(it, 0) }

        return partTwo
    }


    // Test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val (testGroupA, testGroupB) = inputToGroups(testInput)
    check(part1(testGroupA, testGroupB) == 11)

    val input = readInput("Day01")
    val (groupA, groupB) = inputToGroups(input)

    println("Alt solution(s): ${alternateSolutions(input)}")

    println(part1(groupA, groupB))
    println(part2(groupA, groupB))
}
