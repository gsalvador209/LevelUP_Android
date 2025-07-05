package com.tanucode.levelup.ui.space.spaces

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tanucode.levelup.databinding.FragmentSpaceEditorBinding
import com.tanucode.levelup.presentation.ui.space.StickerPickerBottomSheetFragment
import androidx.core.content.edit
import com.tanucode.levelup.util.Constants

class SpaceEditorFragment : Fragment() {

    private var _binding: FragmentSpaceEditorBinding? = null
    private val binding get() = _binding!!

    private val gson = Gson()
    private val prefs by lazy {
        requireContext().getSharedPreferences("spaces_state", Context.MODE_PRIVATE)
    }
    private val spaceKey="current_space"//TODO: Hacerlo dinámico para otros

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

        loadSpaceState()

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
        saveSpaceState()
    }

    override fun onPause() {
        super.onPause()
        saveSpaceState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveSpaceState()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        saveSpaceState()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        loadSpaceState()
    }


    private fun saveSpaceState() {
        // 1) Recorre todos los DraggableStickerView del container
        val states = mutableListOf<StickerState>()
        for (i in 0 until binding.stickerContainer.childCount) {
            val child = binding.stickerContainer.getChildAt(i)
            if (child is DraggableStickerView) {
                val url = child.tag as? String ?: continue
                val matrixVals = child.imageMatrix.toValues()
                states += StickerState(url, matrixVals)
            }
        }
        // 2) Serializa y guarda en prefs
        val json = gson.toJson(states)
        Log.d(Constants.LOGS_MESSAGE,"Saving: $json")
        prefs.edit { putString(spaceKey, json) }
    }

    private fun loadSpaceState() {
        val json = prefs.getString(spaceKey, null) ?: return
        val type = object: TypeToken<List<StickerState>>(){}.type
        val states: List<StickerState> = gson.fromJson(json, type)
        Log.d(Constants.LOGS_MESSAGE,"Retrieving $json")

        // 3) Recrea cada sticker con matrix
        for (st in states) {
            val stickerView = DraggableStickerView(requireContext()).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                tag = st.imageUrl
                // aplica la matrix tras cargar la imagen
                post {
                    imageMatrix = st.matrixValues.toMatrix()
                }
            }
            Glide.with(this)
                .load(st.imageUrl)
                .apply(RequestOptions().override(300,300))
                .into(stickerView)
            binding.stickerContainer.addView(stickerView)
        }
    }
}
