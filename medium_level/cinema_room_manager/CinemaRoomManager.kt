import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter the number of rows:")
    val rows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val seats = scanner.nextInt()

    val cinema = MutableList(rows) { MutableList(seats) { true } }
    var totalIncome = 0
    var currentIncome = 0
    var boughtTickets = 0

    for (i in 0 until rows) {
        for (j in 0 until seats) {
            totalIncome += if (rows * seats > 60 && i >= rows / 2) 8 else 10
        }
    }

    while (true) {
        println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit")
        when (scanner.nextInt()) {
            1 -> showSeats(cinema)
            2 -> {
                val ticketPrice = buyTicket(cinema)
                if (ticketPrice != -1) {
                    boughtTickets++
                    currentIncome += ticketPrice
                }
            }
            3 -> showStatistics(boughtTickets, currentIncome, totalIncome, rows * seats)
            0 -> return
        }
    }
}

fun showSeats(cinema: MutableList<MutableList<Boolean>>) {
    println("\nCinema:")
    print("  ")
    for (i in cinema[0].indices) {
        print("${i + 1} ")
    }
    println()
    for (i in cinema.indices) {
        print("${i + 1} ")
        for (j in cinema[i].indices) {
            print(if (cinema[i][j]) "S " else "B ")
        }
        println()
    }
}

fun buyTicket(cinema: MutableList<MutableList<Boolean>>): Int {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("\nEnter a row number:")
        val row = scanner.nextInt() - 1
        println("Enter a seat number in that row:")
        val seat = scanner.nextInt() - 1

        if (row !in cinema.indices || seat !in cinema[row].indices) {
            println("\nWrong input!")
            continue
        }

        if (!cinema[row][seat]) {
            println("\nThat ticket has already been purchased!")
            continue
        }

        cinema[row][seat] = false
        val ticketPrice = if (cinema.size * cinema[0].size > 60 && row >= cinema.size / 2) 8 else 10
        println("\nTicket price: $$ticketPrice")
        return ticketPrice
    }
}

fun showStatistics(boughtTickets: Int, currentIncome: Int, totalIncome: Int, totalSeats: Int) {
    val percentage = "%.2f".format(boughtTickets.toDouble() / totalSeats * 100)
    println("\nNumber of purchased tickets: $boughtTickets")
    println("Percentage: $percentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}
