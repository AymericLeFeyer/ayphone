package fr.aylabs.ayphone.settings

enum class AppTheme {
    SYSTEM, LIGHT, DARK;

    companion object {
        fun fromString(value: String): AppTheme =
            entries.find { it.name == value } ?: SYSTEM
    }
}
