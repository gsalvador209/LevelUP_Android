package com.tanucode.levelup.ui.space

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.tanucode.levelup.R


class SpaceHomeFragment : Fragment(R.layout.fragment_space_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardStore = view.findViewById<MaterialCardView>(R.id.cardStore)
        val cardSpaces = view.findViewById<MaterialCardView>(R.id.cardSpaces)

        cardStore.setOnClickListener {
            findNavController().navigate(R.id.action_spaceHomeFragment_to_storeFragment)
        }

        cardSpaces.setOnClickListener {
            findNavController().navigate(R.id.action_spaceHomeFragment_to_spacesFragment)
        }
    }
}
