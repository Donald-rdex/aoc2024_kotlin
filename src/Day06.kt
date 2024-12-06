fun main() {


    fun part1(startPt: Pair<Int, Int>, inputGrid: MutableList<MutableList<Char>>): Int {
        println(startPt)
        println(inputGrid)

        val directions = listOf(
            Pair(-1, 0),
            Pair(+1, 0),
            Pair(0, +1),
            Pair(-1, 0))

        var outOfBounds = false
        var steps = 0
        var currx = startPt.first
        var curry = startPt.second
        var direction = 0

        do {
            inputGrid[currx][curry] = 'X'
            steps++

            var nextx = currx + directions[direction].first
            var nexty = curry + directions[direction].second

            if (nextx < 0 || nexty < 0 || nextx >= inputGrid.size || nexty >= inputGrid[0].size) {
                outOfBounds = true
            } else {
                while (inputGrid[nextx][nexty] != '#') {
                    direction++
                    if (direction == 4) { direction = 0 }
                    nextx = currx + directions[direction].first
                    nexty = curry + directions[direction].second
                }
                if (nextx < 0 || nexty < 0 || nextx >= inputGrid.size || nexty >= inputGrid[0].size) {
                    outOfBounds = true
                }
                currx = nextx
                curry = nexty
                steps++
            }

        } while (!outOfBounds)

        return 41
    }

    fun inputToMatrix(input: List<String>): Pair<Pair<Int, Int>, MutableList<MutableList<Char>>> {
        val inputMatrix: MutableList<MutableList<Char>> = mutableListOf<MutableList<Char>>()
        var startPtx = -1
        var startPty = -1

        input.forEachIndexed() { index, line ->
            inputMatrix.add(line.toMutableList())
            if (line.contains("^")) {
                startPtx = index
                startPty = line.indexOf("^")
            }
        }

        return Pair(Pair(startPtx, startPty), inputMatrix)
    }

    // Test if implementation meets criteria from the description, like:
    val (testSt, testInput) = inputToMatrix(readInput("Day06_test"))

    check(part1(testSt, testInput) == 41)

}