import kotlin.random.Random

fun main() {
    val size = 9

    println("How many mines do you want on the field?")
    val mineCount = readln().toIntOrNull() ?: 0

    val minefield = generateMinefield(size, mineCount)
    printMinefield(minefield)
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

fun printMinefield(minefield: MutableList<MutableList<Char>>) {
    for (row in minefield) {
        for (cell in row) {
            print(cell)
        }
        println()
    }
}

