package fr.aylabs.ayphone.di

import fr.aylabs.ayphone.resume.RESUME_MODULE
import fr.aylabs.network.NETWORK_MODULE
import fr.aylabs.settings.SETTINGS_MODULE
import fr.aylabs.settings.createSettingsModule

val SHARED_MODULES = listOf(
    NETWORK_MODULE,
    RESUME_MODULE,
    SETTINGS_MODULE,
    createSettingsModule()
)