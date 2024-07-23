package com.example.first

// Rock,paper and scissor game
fun main()
{
    var computer=0
    var user=0

    println("Press 1:ROck, 2:Paper, 3:Scissor,")
    user=readln().toInt()
    var rand_num = (1..3).random()
    when (rand_num) {
        1 -> computer=1
        2 -> computer=2
        3 -> computer=3
    }

    if(user==computer)
        println("Draw"+" "+computer)
    else if ((user==1 && computer==3) || (user==2 && computer==1) || (user==3 && computer==2))
        println("WINNER"+" "+computer)
    else
        println("Loser"+" "+computer)

}