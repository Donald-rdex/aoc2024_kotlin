import kotlin.math.abs

fun main() {
    val debug = true

    fun part1(rulesMap: Map<Int, Set<Int>>, pageLists: List<List<Int>> ): Int {
        var listSum = 0

        pageLists.forEach { pageList ->
            var containsPages = true
            pageList.forEachIndexed { index, pageNum ->
                val pageOrder = rulesMap[pageNum]
                if (pageOrder != null) {
                    for (page in pageOrder) {
                        containsPages = pageList.subList(index+1, pageList.size).contains(page)
                                || !pageList.subList(0, index).contains(page)
                        if (containsPages && rulesMap.containsKey(page)) {
                            containsPages = !rulesMap[page]!!.contains(pageNum)
                        }

                        if (!containsPages) break
                    }
                }
            }
            if (containsPages) {
                if (debug) println(pageList)
                listSum += pageList[pageList.size/2]
            }
        }
        if (debug) println(listSum)
        return listSum
    }

    fun part2(listA: List<Int>, listB: List<Int>): Int {
        var listSum = 0
        return listSum
    }

    fun parseInput(input: List<String>): Pair<Map<Int, Set<Int>>, List<List<Int>>> {
        val pageRules = mutableMapOf<Int, MutableSet<Int>>()
        val pageList = mutableListOf<List<Int>>()

        val ruleRE = """(\d+)\|(\d+)""".toRegex()
        val listRE = """[0-9]+,.*""".toRegex()
        input.forEach { line ->
            if (ruleRE.matches(line)) {
                val page :Int = ruleRE.matchEntire(line)?.groups?.get(1)?.value!!.toInt()
                val pageAfter :Int = ruleRE.matchEntire(line)?.groups?.get(2)?.value!!.toInt()
                if (pageRules.putIfAbsent(page, mutableSetOf(pageAfter)) != null) {
                    pageRules[page]?.add(pageAfter)
                }
            }
            if (listRE.matches(line)) {
                pageList.add(line.split(",").map { it.toInt() }.toList())
            }
        }

        return Pair(pageRules.toMap(), pageList.toList())
    }


    // Test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val (rulesMapTest, pageListTest) = parseInput(testInput)
    check(part1(rulesMapTest, pageListTest) == 143)

//    val input = readInput("Day05")

//    println(part1(groupA, groupB))
//    println(part2(groupA, groupB))
}
