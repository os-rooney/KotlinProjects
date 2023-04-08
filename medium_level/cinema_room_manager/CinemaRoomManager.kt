fun main() {
  // Read the number of rows from user input
  print("Enter the number of rows: ")
  val numRows = readLine()!!.toInt()

  // Read the number of seats per row from user input
  print("Enter the number of seats on each row: ")
  val numSeatsPerRow = readLine()!!.toInt()

  // Initialize a mutable list to represent the cinema seats
  val cinema = MutableList(numRows) { MutableList(numSeatsPerRow) { 'S' } }

  // Loop to display menu and handle user input
  while(true) {
    println("\n1. Show the seats")
    println("2. Buy a ticket")
    println("0. Exit")

    val choice = readLine()!!.toInt()

    when(choice) {
      1 -> showSeats(cinema)
      2 -> buyTicket(cinema)
      3 -> break
      else -> println("Invalid choice. Please try again.")
    }
  }
}

// Function to display the current seating arrangement
fun showSeats(cinema: MutableList<MutableList<Char>>) {
  println("Cinema: ")
  print("  ")
  for (i in 1..cinema[0].size) {
    print("$i ")
  }
  println()
  for(i in cinema.indices) {
    print("${i + 1}")
    for(j in cinema[i].indices) {
      print("${cinema[i][j]}")
    }
    println()
  }
}

// Function to buy a ticket and update the seating arrangement
fun buyTicket(cinema: MutableList<MutableList<Char>>) {
  print("Enter a row number: ")
  val row = readLIne()!!.toInt() - 1
  print("Enter a seat number in that row: ")
  val seat = readLine()!!.toInt() - 1

  // check if row and seat are within valid range and is free
  if (row in 0 until cinema.size && seat in 0 until cinema[row].size && cinema[row][seat] == 'S') {
    if(cinema[row][seat] == 'B'){
      println("Error! This seat is already take.")
    } else {
      cinema[row][seat] = 'B' // Update seat status to 'B' for taken
      val ticketPric = getTicketPrice(cinema.size, cinema[0].size, row)
      println("Ticket price: $$ticketPrice")
    }
  } else {
    println("Error! Invalid seat selection")
  }
}

// Calculate ticket price
fun getTicketPrice(numRows: Int, numSeatsPerRow: Int, row: Int) : Int {
  val totalSeats =  numRows * numSeatsPerRow
  val frontHalfRows = numRows / 2
  val ticketPrice = if (totalSeats <= 60 || row < frontHalfRows){
    10
  } else {
    8
  }
  return ticketPrice
}
