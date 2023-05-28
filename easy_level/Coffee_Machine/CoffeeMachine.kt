package machine
import java.util.Scanner

class CoffeeMachine(var water: Int, var milk: Int, var coffeeBeans: Int, var cups: Int, var money: Int) {
    val scanner = Scanner(System.`in`)

    fun displayState() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffeeBeans g of coffee beans")
        println("$cups disposable cups")
        println("$money of money\n")
    }

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
        when (scanner.nextInt()) {
            1 -> {
                water -= 250
                coffeeBeans -= 16
                money += 4
            }
            2 -> {
                water -= 350
                milk -= 75
                coffeeBeans -= 20
                money += 7
            }
            3 -> {
                water -= 200
                milk -= 100
                coffeeBeans -= 12
                money += 6
            }
        }
        cups--
    }

    fun fill() {
        println("Write how many ml of water you want to add: ")
        water += scanner.nextInt()
        println("Write how many ml of milk you want to add: ")
        milk += scanner.nextInt()
        println("Write how many grams of coffee beans you want to add: ")
        coffeeBeans += scanner.nextInt()
        println("Write how many disposable cups you want to add: ")
        cups += scanner.nextInt()
    }

    fun take() {
        println("I gave you $$money")
        money = 0
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)
    coffeeMachine.displayState()

    println("Write action (buy, fill, take): ")
    when (Scanner(System.`in`).nextLine()) {
        "buy" -> coffeeMachine.buy()
        "fill" -> coffeeMachine.fill()
        "take" -> coffeeMachine.take()
    }

    coffeeMachine.displayState()
}
