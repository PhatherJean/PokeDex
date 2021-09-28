package com.phatherjay.pokedex.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.pokedex.adapter.PokeAdapter
import com.phatherjay.pokedex.databinding.FragmentPokeMonBinding
import com.phatherjay.pokedex.model.Data
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.utils.ApiState
import com.phatherjay.pokedex.utils.PageAction
import com.phatherjay.pokedex.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeCardFragment : Fragment() {

    private var _binding : FragmentPokeMonBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val pokeAdapter by lazy { PokeAdapter() }
    private val pokeQue by lazy { PokeQue(2,10) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPokeMonBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (pokeViewModel.pokeQue == null) viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//
//        }
        initViews()
        setupObservers()
    }


    private fun initViews() = with(binding) {
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    Toast.makeText(context,"TOP of PAGE",Toast.LENGTH_SHORT).show()
                } else if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    Toast.makeText(context, "BOTTOM of PAGE", Toast.LENGTH_SHORT).show()
                    pokeViewModel.fetchPokeData(PageAction.NEXT)
                }
            }
        })
        pokeViewModel.fetchPokeData(pokeQue)
    }

    private fun setupObservers() = with(pokeViewModel) {
        pokeState.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.isVisible = state is ApiState.Loading
            if (state is ApiState.Success) loadX(state.data.data as List<Data>)
            if (state is ApiState.Failure) handleFailure(state.errorMsg)
        }
    }

    private fun loadX(pokemon: List<Data>) = with(binding.rvList) {
        if (adapter == null) adapter = pokeAdapter
        pokeAdapter.updateList(pokemon)
    }

    private fun handleFailure(errorMsg: String) {
        Log.d("HandleFailure", "ApiState.Failure: $errorMsg")
    }

}