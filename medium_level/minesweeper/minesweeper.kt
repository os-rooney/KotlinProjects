import kotlin.random.Random

fun main() {
    val size = 9
    val mineCount = 10

    val minefield = generateMinefield(size, mineCount)
    printMinefield(minefield)
}

fun generateMinefield(size: Int, mineCount: Int): Array<Array<Char>> {
    val minefield = Array(size) { Array(size) { '.' } }

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

fun printMinefield(minefield: Array<Array<Char>>) {
    for (row in minefield) {
        for (cell in row) {
            print(cell)
        }
        println()
    }
}
