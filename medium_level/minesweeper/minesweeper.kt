import kotlin.random.Random

fun main() {
    val size = 9

    println("How many mines do you want on the field?")
    val mineCount = readLine()?.toIntOrNull() ?: 0

    val minefield = generateMinefield(size, mineCount)
    val fieldWithHints = addHintsToMinefield(minefield)
    printMinefield(fieldWithHints)
}

fun generateMinefield(size: Int, mineCount: Int): MutableList<MutableList<Char>> {
    val minefield = MutableList(size) { MutableList(size) { '.' } }

    repeat(mineCount) {
        var row: Int
        var col: Int
        do {
            row = Random.nextInt(size)
            col = Random.nextInt(size)
        } while (minefield[row][col] == 'X')

        minefield[row][col] = 'X'
    }

    return minefield
}

fun addHintsToMinefield(minefield: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    val size = minefield.size

    for (row in 0 until size) {
        for (col in 0 until size) {
            if (minefield[row][col] == '.') {
                val hintCount = countMinesAroundCell(minefield, row, col)
                if (hintCount > 0) {
                    minefield[row][col] = hintCount.toString().first()
                }
            }
        }
    }

    return minefield
}

fun countMinesAroundCell(minefield: MutableList<MutableList<Char>>, row: Int, col: Int): Int {
    val size = minefield.size
    var mineCount = 0

    for (i in row - 1..row + 1) {
        for (j in col - 1..col + 1) {
            if (i in 0 until size && j in 0 until size && minefield[i][j] == 'X') {
                mineCount++
            }
        }
    }

    return mineCount
}

fun printMinefield(minefield: MutableList<MutableList<Char>>) {
    for (row in minefield) {
        for (cell in row) {
            print(cell)
        }
        println()
    }
}

