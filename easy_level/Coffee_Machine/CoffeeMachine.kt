import java.util.Scanner

class CoffeeMachine(private var water: Int, private var milk: Int, private var coffeeBeans: Int, private var cups: Int, private var money: Int) {
    private var state = "choosing action"

    fun displayState() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$coffeeBeans g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money\n")
    }

    private fun canMakeCoffee(waterNeeded: Int, milkNeeded: Int, coffeeBeansNeeded: Int): Boolean {
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

    fun process(input: String) {
        when (state) {
            "choosing action" -> {
                when (input) {
                    "buy" -> {
                        state = "choosing coffee"
                        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                    }
                    "fill" -> {
                        state = "filling water"
                        println("Write how many ml of water you want to add: ")
                    }
                    "take" -> {
                        println("I gave you $$money")
                        money = 0
                        state = "choosing action"
                    }
                    "remaining" -> {
                        displayState()
                        state = "choosing action"
                    }
                    "exit" -> state = "exit"
                }
            }
            "choosing coffee" -> {
                when (input) {
                    "1" -> {
                        if (canMakeCoffee(250, 0, 16)) {
                            water -= 250
                            coffeeBeans -= 16
                            money += 4
                            cups--
                        }
                        state = "choosing action"
                    }
                    "2" -> {
                        if (canMakeCoffee(350, 75, 20)) {
                            water -= 350
                            milk -= 75
                            coffeeBeans -= 20
                            money += 7
                            cups--
                        }
                        state = "choosing action"
                    }
                    "3" -> {
                        if (canMakeCoffee(200, 100, 12)) {
                            water -= 200
                            milk -= 100
                            coffeeBeans -= 12
                            money += 6
                            cups--
                        }
                        state = "choosing action"
                    }
                    "back" -> state = "choosing action"
                }
            }
            "filling water" -> {
                water += input.toInt()
                println("Write how many ml of milk you want to add: ")
                state = "filling milk"
            }
            "filling milk" -> {
                milk += input.toInt()
                println("Write how many grams of coffee beans you want to add: ")
                state = "filling beans"
            }
            "filling beans" -> {
                coffeeBeans += input.toInt()
                println("Write how many disposable cups you want to add: ")
                state = "filling cups"
            }
            "filling cups" -> {
                cups += input.toInt()
                state = "choosing action"
            }
        }
    }

    fun getState(): String {
        return state
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)

    while (true) {
        val input = scanner.nextLine()
        coffeeMachine.process(input)
        if (coffeeMachine.getState() == "exit") {
            break
        }
    }
}

