package fr.aylabs.ayphone.hobbies.domain.models

sealed interface HobbiesAction {
    data class OpenUrl(val url: String) : HobbiesAction
    data object OpenAyLabs : HobbiesAction
}
