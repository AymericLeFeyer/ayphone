package fr.aylabs.ayphone.di

import fr.aylabs.ayphone.about.ABOUT_MODULE
import fr.aylabs.ayphone.missions.MISSIONS_MODULE
import fr.aylabs.ayphone.resume.RESUME_MODULE
import fr.aylabs.ayphone.stack.STACK_MODULE
import fr.aylabs.network.NETWORK_MODULE
import fr.aylabs.settings.SETTINGS_MODULE
import fr.aylabs.settings.createSettingsModule

val SHARED_MODULES = listOf(
    NETWORK_MODULE,
    RESUME_MODULE,
    MISSIONS_MODULE,
    STACK_MODULE,
    ABOUT_MODULE,
    SETTINGS_MODULE,
    createSettingsModule()
)
