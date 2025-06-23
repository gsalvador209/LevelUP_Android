package com.tanucode.levelup.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserAvatarUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserNameUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    getUser: GetUserUseCase,
    private val updateName: UpdateUserNameUseCase,
    private val updateAvatar: UpdateUserAvatarUseCase
) : ViewModel() {

    val userState: StateFlow<UserEntity> = getUser()
        .stateIn(viewModelScope, SharingStarted.Lazily, UserEntity(name = "Usuario"))

    fun onSaveName(name: String) = viewModelScope.launch {
        updateName(name)
    }

    fun onAvatarPicked(uri: String) = viewModelScope.launch {
        updateAvatar(uri)
    }
}
