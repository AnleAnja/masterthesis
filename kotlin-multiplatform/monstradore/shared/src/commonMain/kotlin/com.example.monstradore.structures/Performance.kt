package com.example.monstradore.structures

fun Factorial(count: Long): Long {
    if(count == 0L) return 0
    var i = count-1
    var result = count
    while (i > 0) {
        result *= i
        i--
    }
    return result
}