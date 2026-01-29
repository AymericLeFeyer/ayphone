package fr.aylabs.network

import io.ktor.client.plugins.logging.Logger

class AyLogger : Logger {
    override fun log(message: String) {
        co.touchlab.kermit.Logger.d(message)
    }
}