package com.example.monstradore.structures

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
