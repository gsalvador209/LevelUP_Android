package com.tanucode.levelup.presentation.ui.space

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.lifecycle.lifecycleScope
import com.tanucode.levelup.R
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.databinding.FragmentStickerPickerBinding
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StickerPickerBottomSheetFragment(
    private val onStickerSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentStickerPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStickerPickerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = StickerPickerAdapter { url ->
            onStickerSelected(url)
            dismiss()
        }

        binding.recyclerStickers.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            this.adapter = adapter
        }

        // Carga los stickers comprados
        lifecycleScope.launch {
            val app = requireActivity().application as LevelUpApp
            val products = app.getProductsUseCase()
            Log.d(Constants.LOGS_MESSAGE, "▶ productos totales: ${products.size}")
            val purchased = app.getPurchasedProductsUseCase(Constants.USER_ID_SINGLETON).first()
            Log.d(Constants.LOGS_MESSAGE, "▶ IDs comprados: $purchased")
            val filtered = products.filter {it.id in purchased}
            if (filtered.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.recyclerStickers.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.recyclerStickers.visibility = View.VISIBLE
                adapter.submitList(filtered)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
