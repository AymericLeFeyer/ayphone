package fr.aylabs.settings

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module


actual fun createSettingsModule() = module {
    single<Settings> {
        val context: Context = get()
        val delegate: SharedPreferences =
            context.getSharedPreferences("AndroidSettings", Context.MODE_PRIVATE)
        SharedPreferencesSettings(delegate)
    }
}