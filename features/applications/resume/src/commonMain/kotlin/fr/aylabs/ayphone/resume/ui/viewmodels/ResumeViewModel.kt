package fr.aylabs.ayphone.resume.ui.viewmodels

import androidx.lifecycle.ViewModel
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase

class ResumeViewModel(
    private val getResumeUseCase: GetResumeUseCase
) : ViewModel() {
    suspend fun init() {
        getResumeUseCase().also { println(it) }
    }
}