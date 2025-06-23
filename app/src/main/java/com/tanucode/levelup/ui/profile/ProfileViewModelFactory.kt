package com.tanucode.levelup.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserAvatarUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserNameUseCase

class ProfileViewModelFactory(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserAvatarUseCase: UpdateUserAvatarUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                getUserUseCase,
                updateUserNameUseCase,
                updateUserAvatarUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
