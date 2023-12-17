import kotlin.random.Random
import kotlin.io.readlnOrNull as readlnOrNull1

fun main() {

    val cities = Cities().citiesOfRussia

    val usedCities = mutableListOf<String>()

    println("Добро пожаловать в игру \"Города России\"!")

    var currentPlayer = 0 // 0 - игрок, 1 - компьютер
    var lastCity = ""

    while (true) {
        if (currentPlayer == 0) {
            println("Ваш ход. Введите название города:")
            val playerCity = readlnOrNull1()?.trim()
                ?.capitalize()

            if (playerCity != null && isValidCity(playerCity, lastCity, usedCities)) {
                usedCities.add(playerCity)
                lastCity = playerCity
                currentPlayer = 1
            } else {
                println("Некорректный город. Попробуйте ещё раз.")
            }
        } else {
            val computerCity = generateComputerCity(cities, lastCity, usedCities)
            if (computerCity.isNotEmpty()) {
                usedCities.add(computerCity)
                lastCity = computerCity
                currentPlayer = 0
                println("Ход компьютера: $computerCity")
            } else {
                println("Компьютер не может найти город. Вы победили!")
                break
            }
        }
    }
}

fun isValidCity(city: String, lastCity: String, usedCities: List<String>): Boolean {
    return city.isNotEmpty() && !usedCities.contains(city) && (lastCity.isEmpty() || city.startsWith(lastCity.last(), ignoreCase = true))
}

fun generateComputerCity(cities: List<String>, lastCity: String, usedCities: List<String>): String {
    val filteredCities = cities.filter { it.startsWith(lastCity.last(), ignoreCase = true) && !usedCities.contains(it) }
    return if (filteredCities.isNotEmpty()) {
        filteredCities[Random.nextInt(filteredCities.size)]
    } else {
        ""
    }
}