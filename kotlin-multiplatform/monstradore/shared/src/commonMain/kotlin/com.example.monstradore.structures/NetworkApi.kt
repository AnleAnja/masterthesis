package com.example.monstradore.structures

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NetworkApi {
    private val client = HttpClient()

    suspend fun getResponse(): String {
        val response = client.get(
            "https://jsonplaceholder.typicode.com/posts/1"
        )
        return response.bodyAsText()
    }
}