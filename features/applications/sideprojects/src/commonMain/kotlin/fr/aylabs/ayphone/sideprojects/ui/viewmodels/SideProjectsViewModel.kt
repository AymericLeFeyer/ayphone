package fr.aylabs.ayphone.sideprojects.ui.viewmodels

import fr.aylabs.ayphone.missions.ui.viewmodels.MissionsViewModel
import fr.aylabs.ayphone.resume.domain.usecases.GetResumeUseCase

class SideProjectsViewModel(
    getResumeUseCase: GetResumeUseCase,
) : MissionsViewModel(getResumeUseCase, { it.isSideProject })
