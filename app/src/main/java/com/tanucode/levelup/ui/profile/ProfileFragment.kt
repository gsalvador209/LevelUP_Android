package com.tanucode.levelup.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.tanucode.levelup.R
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.databinding.FragmentProfileBinding
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserAvatarUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserNameUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userRepo by lazy {
        (requireActivity().application as LevelUpApp).userRepository
    }

    private val getUserUC       by lazy { GetUserUseCase(userRepo) }
    private val updateNameUC    by lazy { UpdateUserNameUseCase(userRepo) }
    private val updateAvatarUC  by lazy { UpdateUserAvatarUseCase(userRepo) }

    private val vm: ProfileViewModel by viewModels {
        ProfileViewModelFactory(getUserUC,updateNameUC, updateAvatarUC)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCollectors()

        binding.btnSave.setOnClickListener {
            val newName = binding.etName.text.toString().trim()
            if (newName.isNotEmpty()) vm.onSaveName(newName)
        }

        binding.ivAvatar.setOnClickListener {

        }
    }

    private fun setupCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.userState.collectLatest { user ->
                    // aquí binding siempre NO nulo
                    binding.etName.setText(user.name)
                    binding.tvGoldCoins.text   = "${user.goldCoins.toInt()}"
                    binding.tvSilverCoins.text = "${user.silverCoins.toInt()}"

                    Glide.with(binding.ivAvatar.context)
                        .load(user.avatarUri)
                        .placeholder(R.drawable.default_user)
                        .into(binding.ivAvatar)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
