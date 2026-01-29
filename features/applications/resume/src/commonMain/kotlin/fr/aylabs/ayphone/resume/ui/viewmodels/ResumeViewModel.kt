package fr.aylabs.ayphone.resume.ui.viewmodels

import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase
) {
    suspend fun init() {
        getResumeUseCase().also { println(it) }
    }
}