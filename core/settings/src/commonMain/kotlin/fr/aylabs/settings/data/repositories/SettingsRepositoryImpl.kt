package fr.aylabs.settings.data.repositories

import fr.aylabs.settings.data.datasources.SettingsDatasource
import fr.aylabs.settings.domain.enums.SettingsKeys
import fr.aylabs.settings.domain.repositories.SettingsRepository

class SettingsRepositoryImpl(private val settingsDatasource: SettingsDatasource) :
    SettingsRepository {
    override fun set(key: SettingsKeys, value: Boolean) {
        settingsDatasource.set(key, value)
    }

    override fun set(key: SettingsKeys, value: String) {
        settingsDatasource.set(key, value)
    }

    override fun get(key: SettingsKeys, defaultValue: Boolean?): Boolean {
        return settingsDatasource.get(key, defaultValue)
    }

    override fun get(key: SettingsKeys, defaultValue: String?): String {
        return settingsDatasource.get(key, defaultValue)
    }

}