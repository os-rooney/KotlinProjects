package tictactoe
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val cells = MutableList(9) { '_' }
    printGrid(cells.joinToString(""))
    var turn = 'X'
    while (true) {
        print("Enter the coordinates: ")
        val row = scanner.nextInt()
        val col = scanner.nextInt()
        val result = makeMove(cells, row, col, turn)
        if (result == "Success") {
            printGrid(cells.joinToString(""))
            val gameState = analyzeGameState(cells.joinToString(""))
            if (gameState != "Game not finished") {
                println(gameState)
                break
            }
            turn = if (turn == 'X') 'O' else 'X'
        } else {
            println(result)
        }
    }
}

fun printGrid(cells: String) {
    println("---------")
    for (i in cells.indices step 3) {
        println("| ${cells[i]} ${cells[i + 1]} ${cells[i + 2]} |")
    }
    println("---------")
}

fun makeMove(cells: MutableList<Char>, row: Int, col: Int, turn: Char): String {
    return if (row !in 1..3 || col !in 1..3) {
        "Coordinates should be from 1 to 3!"
    } else {
        val index = 3 * (row - 1) + (col - 1)
        if (cells[index] != '_') {
            "This cell is occupied! Choose another one!"
        } else {
            cells[index] = turn
            "Success"
        }
    }
}

fun analyzeGameState(cells: String): String {
    val rows = listOf(
        cells.substring(0..2),
        cells.substring(3..5),
        cells.substring(6..8),
        cells.filterIndexed { index, _ -> index % 3 == 0 },
        cells.filterIndexed { index, _ -> index % 3 == 1 },
        cells.filterIndexed { index, _ -> index % 3 == 2 },
        cells.filterIndexed { index, _ -> index % 4 == 0 },
        cells.filterIndexed { index, _ -> index % 2 == 0 && index in 2..6 }
    )

    val xWins = rows.any { it == "XXX" }
    val oWins = rows.any { it == "OOO" }
    val emptyCount = cells.count { it == '_' }

    return when {
        xWins -> "X wins"
        oWins -> "O wins"
        emptyCount > 0 -> "Game not finished"
        else -> "Draw"
    }
}

