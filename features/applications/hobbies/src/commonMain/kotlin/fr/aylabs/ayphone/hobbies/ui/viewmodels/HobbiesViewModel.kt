package fr.aylabs.ayphone.hobbies.ui.viewmodels

import androidx.lifecycle.ViewModel
import fr.aylabs.ayphone.hobbies.domain.models.HobbiesSection
import fr.aylabs.ayphone.hobbies.domain.usecases.GetHobbiesDataUseCase

class HobbiesViewModel(
    getHobbiesDataUseCase: GetHobbiesDataUseCase,
) : ViewModel() {
    val sections: List<HobbiesSection> = getHobbiesDataUseCase()
}
