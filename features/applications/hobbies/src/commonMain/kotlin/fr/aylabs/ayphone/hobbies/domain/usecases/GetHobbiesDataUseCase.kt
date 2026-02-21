package fr.aylabs.ayphone.hobbies.domain.usecases

import fr.aylabs.ayphone.hobbies.domain.models.HobbiesSection
import fr.aylabs.ayphone.hobbies.domain.repositories.HobbiesRepository

class GetHobbiesDataUseCase(private val repository: HobbiesRepository) {
    operator fun invoke(): List<HobbiesSection> = repository.getSections()
}
