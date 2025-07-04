package com.tanucode.levelup.ui.space.spaces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tanucode.levelup.databinding.FragmentSpaceEditorBinding
import com.tanucode.levelup.presentation.ui.space.StickerPickerBottomSheetFragment

class SpaceEditorFragment : Fragment() {

    private var _binding: FragmentSpaceEditorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpaceEditorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddSticker.setOnClickListener {
            showStickerPicker()
        }
    }

    private fun showStickerPicker() {
        StickerPickerBottomSheetFragment {imageUrl ->
            addSticker(imageUrl)
        }.show(childFragmentManager,"sticker_picker")
    }

    private fun addSticker(imageUrl: String) {
        // 1) Creas la vista que contiene gestos
        val stickerView = DraggableStickerView(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }
        // 2) La cargas con Glide
        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions().override(300, 300))
            .into(stickerView)
        // 3) La añades al container
        binding.stickerContainer.addView(stickerView)
    }
}
