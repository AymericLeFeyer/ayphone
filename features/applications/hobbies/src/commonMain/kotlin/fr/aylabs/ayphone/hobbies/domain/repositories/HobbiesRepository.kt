package fr.aylabs.ayphone.hobbies.domain.repositories

import fr.aylabs.ayphone.hobbies.domain.models.HobbiesSection

interface HobbiesRepository {
    fun getSections(): List<HobbiesSection>
}
