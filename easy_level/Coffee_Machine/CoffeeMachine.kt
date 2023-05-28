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

    fun canMakeCoffee(waterNeeded: Int, milkNeeded: Int, coffeeBeansNeeded: Int): Boolean {
        if (water < waterNeeded) {
            println("Sorry, not enough water!")
            return false
        }
        if (milk < milkNeeded) {
            println("Sorry, not enough milk!")
            return false
        }
        if (coffeeBeans < coffeeBeansNeeded) {
            println("Sorry, not enough coffee beans!")
            return false
        }
        if (cups < 1) {
            println("Sorry, not enough disposable cups!")
            return false
        }
        println("I have enough resources, making you a coffee!")
        return true
    }

    fun buy() {
        while (true) {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
            val choice = scanner.nextLine()
            when (choice) {
                "1" -> {
                    if (canMakeCoffee(250, 0, 16)) {
                        water -= 250
                        coffeeBeans -= 16
                        money += 4
                        cups--
                    }
                    return
                }
                "2" -> {
                    if (canMakeCoffee(350, 75, 20)) {
                        water -= 350
                        milk -= 75
                        coffeeBeans -= 20
                        money += 7
                        cups--
                    }
                    return
                }
                "3" -> {
                    if (canMakeCoffee(200, 100, 12)) {
                        water -= 200
                        milk -= 100
                        coffeeBeans -= 12
                        money += 6
                        cups--
                    }
                    return
                }
                "back" -> return
            }
        }
    }

    fun fill() {
        println("Write how many ml of water you want to add: ")
        water += scanner.nextInt()
        scanner.nextLine()  // to consume the newline left-over
        println("Write how many ml of milk you want to add: ")
        milk += scanner.nextInt()
        scanner.nextLine()
        println("Write how many grams of coffee beans you want to add: ")
        coffeeBeans += scanner.nextInt()
        scanner.nextLine()
        println("Write how many disposable cups you want to add: ")
        cups += scanner.nextInt()
        scanner.nextLine()
    }

    fun take() {
        println("I gave you $$money")
        money = 0
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)

    while (true) {
        println("\nWrite action (buy, fill, take, remaining, exit): ")
        when (Scanner(System.`in`).nextLine()) {
            "buy" -> coffeeMachine.buy()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "remaining" -> coffeeMachine.displayState()
            "exit" -> return
        }
    }
}

