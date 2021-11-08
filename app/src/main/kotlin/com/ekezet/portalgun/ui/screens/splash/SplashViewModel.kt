package com.ekezet.portalgun.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekezet.portalgun.base.OpState
import com.ekezet.portalgun.base.contracts.repos.ICharacterRepo
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SplashViewModel @Inject constructor(
    characterRepo: ICharacterRepo,
) : ViewModel(), SplashContract.ViewModel {

    override val isLoading = characterRepo.items
        .map { result -> result is OpState.Loading }

    override val lastError = characterRepo.items
        .map { result -> result.throwable }

    init {
        viewModelScope.launch {
            characterRepo.fetchPage().await()
        }
    }
}
