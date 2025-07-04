package com.tanucode.levelup.presentation.ui.store

import BuyProductUseCase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.databinding.FragmentStoreBinding
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.presentation.viewmodel.StoreViewModel

class StoreFragment : Fragment() {

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoreViewModel by viewModels {
        StoreViewModelFactory(
            (requireActivity().application as LevelUpApp).getProductsUseCase,
            (requireActivity().application as LevelUpApp).getPurchasedProductsUseCase,
            (requireActivity().application as LevelUpApp).buyProductUseCase
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pbLoading.visibility = View.VISIBLE
        val adapter = StoreAdapter { product ->

            viewModel.onBuy(product)

        }
        binding.recyclerStore.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            this.adapter = adapter
        }

        viewModel.products.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.pbLoading.visibility = View.GONE
        }
        viewModel.purchasedIds.observe(viewLifecycleOwner) { ids ->
            adapter.setPurchasedIds(ids)
        }
        viewModel.buyResult.observe(viewLifecycleOwner) { success ->
            if (!success) {
                Snackbar.make(binding.root,
                    "No tienes monedas suficientes",
                    Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
