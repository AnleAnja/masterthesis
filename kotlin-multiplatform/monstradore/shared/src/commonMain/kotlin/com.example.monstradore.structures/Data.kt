package com.example.monstradore.structures

data class Category(val name: String, val features: List<String>)

object Features {
    val ux = Category("UI/UX",
        listOf(
            "Reichhaltige UI Elemente",
            "Interaktionsdesign",
            "Gesten",
            "Navigation",
            "Eingabemethoden",
            "Multimedia",
            "Animationen",
            "2D und 3D Grafiken"
        )
    )
    val functions = Category("Gerätespezifische Funktionen",
        listOf(
            "Netzwerkcalls",
            "Dateizugriff + Persistierung",
            "Zugriff auf native Anwendungen",
            "Kamera",
            "GPS",
            "Beschleunigung",
            "Fingerabdruck / Face ID"
        )
    )
    val overview = listOf(ux, functions)
}