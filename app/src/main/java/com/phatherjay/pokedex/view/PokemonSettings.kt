package com.phatherjay.pokedex.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.phatherjay.pokedex.R
import com.phatherjay.pokedex.databinding.FragmentPokeMonSettingsBinding
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.repo.local.utils.dataStore
import com.phatherjay.pokedex.utils.PreferenceKeys
import com.phatherjay.pokedex.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint
/**
 * A simple [Fragment] subclass.
 * Use the [PokemonSettings.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PokemonSettings : Fragment(R.layout.fragment_poke_mon_settings) {
    private lateinit var _binding: FragmentPokeMonSettingsBinding
    private val binding get() = _binding
    private val pokeViewModel by activityViewModels<PokeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= FragmentPokeMonSettingsBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokeMonSettingsBinding.bind(view)
        initView()
    }

    private fun initView()  = with(binding){
        pokeViewModel.pokeQue?.let { sliderPoke.value = (it.pageSize?.toFloat() ?: 10) as Float }
        sliderPoke.addOnChangeListener { _, _, _ -> toggleSubmit() }
        btnSubmit.setOnClickListener {
            val queries = getPokeQue()

            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                it.context.dataStore.edit { settings ->
                    queries.pageSize?.let {
                        settings[PreferenceKeys.PAGESIZE] = it
                    }
                    settings[PreferenceKeys.PAGE]
                }
            }
            pokeViewModel.fetchPokeData(queries)
            findNavController().navigateUp()
        }
    }

    private fun toggleSubmit(){
        Log.e(TAG, "toggleSubmit: ${validateQuery()}", )
        binding.btnSubmit.isVisible = validateQuery()
    }

    private fun validateQuery(): Boolean {
        val newQuery = getPokeQue()
        newQuery.page = 1
        return pokeViewModel.pokeQue?.let {
            return@let   (it.pageSize != newQuery.pageSize && newQuery.pageSize!! >= 10)
        } ?: (newQuery.pageSize != null && newQuery.pageSize!! >= 10)
    }

    private fun getPokeQue() = PokeQue(
        pageSize = binding.sliderPoke.value.toInt(),
        page = pokeViewModel.pokeQue?.page
    )
    companion object { private const val TAG  = "Poke Settings" }
}