package com.phatherjay.pokedex.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.phatherjay.pokedex.databinding.FragmentPokeMonBinding
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint


class PokeCardFragment : Fragment() {

    private var _binding : FragmentPokeMonBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private var pokeQue= PokeQue(1,10,"")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPokeMonBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("onViewCreated", "Before We got called to fetch data $pokeQue")
        pokeQue?.let {
            Log.e("onViewCreated", "After We got called to fetch data $pokeQue")
            pokeViewModel.fetchPokeData(it)
        }
    }
}