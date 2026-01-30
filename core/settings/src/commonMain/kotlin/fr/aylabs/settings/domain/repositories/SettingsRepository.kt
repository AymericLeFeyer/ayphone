package fr.aylabs.settings.domain.repositories

import fr.aylabs.settings.domain.enums.SettingsKeys

interface SettingsRepository {
    fun set(key: SettingsKeys, value: Boolean)
    fun set(key: SettingsKeys, value: String)
    fun get(key: SettingsKeys, defaultValue: Boolean?): Boolean
    fun get(key: SettingsKeys, defaultValue: String?): String
}