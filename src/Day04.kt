fun main() {
    fun checkIndexNN(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x > 3) {
            if (input[x][y] == 'X' && input[x-1][y] == 'M' && input[x-2][y] == 'A' && input[x-3][y] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexSS(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x < input.size - 3) {
            if (input[x][y] == 'X' && input[x+1][y] == 'M' && input[x+2][y] == 'A' && input[x+3][y] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexEE(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (y <= input[x].size - 3) {
            if (input[x][y] == 'X' && input[x][y+1] == 'M' && input[x][y + 2] == 'A' && input[x][y + 3] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexWW(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (y > 3) {
            if (input[x][y] == 'X' && input[x][y - 1] == 'M' && input[x][y-2] == 'A' && input[x][y-3]=='S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexSE(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x < input.size - 3 && y < input[x].size - 3) {
            if (input[x][y] == 'X' && input[x+1][y+1] == 'M' && input[x+2][y+2] == 'A' && input[x+3][y+3] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexSW(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x < input.size - 3 && y > 3) {
            if (input[x][y] == 'X' && input[x+1][y-1] == 'M' && input[x+2][y-2] == 'A' && input[x+3][y-3] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexNE(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x > 3 && y < input[x].size - 3) {
            if (input[x][y] == 'X' && input[x-1][y+1] == 'M' && input[x-2][y+2] == 'A' && input[x-3][y+3] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun checkIndexNW(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
        if (x > 3 && y > 3) {
            if (input[x][y] == 'X' && input[x-1][y-1] == 'M' && input[x-2][y-2] == 'A' && input[x-3][y-3] == 'S') {
                print(input[x][y])
                return 1
            }
        }
        print(input[x][y].lowercaseChar())
        return 0
    }

    fun part1(input: MutableList<MutableList<Char>>): Int {
        var listSum = 0
        var oldListSum = listSum

        for (x in input.indices) {
            for (y in input[x].indices) {
                when (input[x][y]) {
                    'X' -> {
                        listSum += checkIndexNN(input, x, y); print("$listSum")
                        listSum += checkIndexSS(input, x, y); print("$listSum")
                        listSum += checkIndexEE(input, x, y); print("$listSum")
                        listSum += checkIndexWW(input, x, y); print("$listSum")
                        listSum += checkIndexSE(input, x, y); print("$listSum")
                        listSum += checkIndexSW(input, x, y); print("$listSum")
                        listSum += checkIndexNE(input, x, y); print("$listSum")
                        listSum += checkIndexNW(input, x, y); print("$listSum")
                        if (listSum != oldListSum) {
                            oldListSum = listSum
                            print(input[x][y])
                        } else {
                            print("o")
                        }
                    }
                    else -> print(".")
                }
            }
            println("  $listSum")
        }
        println(listSum)
        return listSum
    }

    fun part2(input: List<String>): Int {
        var listSum = 0
        return listSum
    }

    fun inputToGrid(input: List<String>): MutableList<MutableList<Char>> {
        val inputGrid = mutableListOf<MutableList<Char>>()

        for ((i, line) in input.withIndex()) {
            inputGrid.add(i, mutableListOf())
            for (j in line.indices) {
                inputGrid[i].add(j, line[j])
            }
        }

        return inputGrid
    }

    // Test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testInputGrid = inputToGrid(testInput)

    check(part1(testInputGrid) == 18)

    val input = readInput("Day04")
    val inputGrid = inputToGrid(input)
    println(part1(inputGrid))

//    check(part2(testInputGrid) == 0)
//    println(part2(inputGrid))

}
