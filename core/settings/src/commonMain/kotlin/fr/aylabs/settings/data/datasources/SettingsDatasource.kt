package fr.aylabs.settings.data.datasources

import com.russhwolf.settings.Settings
import fr.aylabs.settings.domain.enums.SettingsKeys

class SettingsDatasource(private val settings: Settings) {
    fun set(key: SettingsKeys, value: String) {
        settings.putString(key.name, value)
    }

    fun set(key: SettingsKeys, value: Boolean) {
        settings.putBoolean(key.name, value)
    }

    fun get(key: SettingsKeys, defaultValue: String?): String {
        return settings.getString(key.name, defaultValue ?: "")
    }

    fun get(key: SettingsKeys, defaultValue: Boolean?): Boolean {
        return settings.getBoolean(key.name, defaultValue ?: false)
    }
}