package fr.aylabs.ayphone.aylabs.data.datasources

import com.russhwolf.settings.Settings

class AyLabsLocalDatasource(private val settings: Settings) {

    fun getCachedJson(): String? {
        val json = settings.getStringOrNull(KEY_JSON)
        return if (json.isNullOrBlank()) null else json
    }

    fun getCachedTimestamp(): Long? {
        val ts = settings.getLongOrNull(KEY_TIMESTAMP)
        return if (ts == null || ts == 0L) null else ts
    }

    fun saveCache(json: String, timestamp: Long) {
        settings.putString(KEY_JSON, json)
        settings.putLong(KEY_TIMESTAMP, timestamp)
    }

    companion object {
        private const val KEY_JSON = "aylabs_cache_json"
        private const val KEY_TIMESTAMP = "aylabs_cache_timestamp"
    }
}
