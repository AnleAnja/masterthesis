package com.example.monstradore.storage

class UserStorage(private val storage: Storage) {
    private val key = "com.example.monstradore.users"

    fun saveUsers(users: List<String>) =
        storage.saveStrings(users, key)

    fun getUsers(): List<String> =
        storage.getStrings(key)
}