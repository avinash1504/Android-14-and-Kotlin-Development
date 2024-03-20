package com.example.rockpaperscissors

fun main() {
    val playerChoice = playerChoices()
    val computerChoice = computerChoices()
    result(playerChoice, computerChoice)
}

fun computerChoices(): String {
    val randomNumber = (1..3).random()
    val computerChoice = when (randomNumber) {
        1 -> "Rock"
        2 -> "Paper"
        3 -> "Scissors"
        else -> ""
    }
    println("Computer chose: $computerChoice")
    return computerChoice
}

fun playerChoices(): String {
    while(true) {
        println("Rock, Paper, or Scissors? Enter Your choice:")
        val playerChoice = readln().capitalize() // Adjust case to ensure consistent comparison
        if (playerChoice == "Rock" || playerChoice == "Paper" || playerChoice == "Scissors") {
            println("You chose $playerChoice")

        }else {
            println("Invalid choice. Please enter Rock, Paper, or Scissors.")
        }
        return playerChoice
    }

}

fun result(playerChoice: String, computerChoice: String) {
    val winner = when {
        playerChoice == computerChoice -> "It's a tie!"
        playerChoice == "Rock" && computerChoice == "Scissors" ||
                playerChoice == "Paper" && computerChoice == "Rock" ||
                playerChoice == "Scissors" && computerChoice == "Paper" -> "Player wins!"
        else -> "Computer wins!"
    }
    println(winner)
}
