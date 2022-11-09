package com.example.monstradore.structures

fun factorial(count: Long): Long {
    if(count == 0L) return 0
    var i = count-1
    var result = count
    while (i > 0) {
        result *= i
        i--
    }
    return result
}

fun prime(n: Int): Int {
    var num = 1
    var count=0
    while (count < n) {
        num++
        var i = 2
        while(i <= num) {
            if (num % i == 0) {
                break
            }
            i++
        }
        if (i == num) {
            count ++
        }
    }
    return num
}