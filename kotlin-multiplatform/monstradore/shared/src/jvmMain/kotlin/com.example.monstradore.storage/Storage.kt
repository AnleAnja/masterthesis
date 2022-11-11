package com.example.monstradore.storage

actual typealias Storage = Nothing

actual fun Storage.saveStrings(strings: List<String>, key: String) {}

actual fun Storage.getStrings(key: String): List<String> = emptyList()