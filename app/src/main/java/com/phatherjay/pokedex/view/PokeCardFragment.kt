package com.phatherjay.pokedex.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.pokedex.adapter.PokeAdapter
import com.phatherjay.pokedex.databinding.FragmentPokeMonBinding
import com.phatherjay.pokedex.model.requests.PokeQue
import com.phatherjay.pokedex.utils.PageAction
import com.phatherjay.pokedex.viewmodel.PokeViewModel
import dagger.hilt.android.AndroidEntryPoint


class PokeCardFragment : Fragment() {

    private var _binding : FragmentPokeMonBinding? = null
    private val binding get() = _binding!!
    private val pokeViewModel by activityViewModels<PokeViewModel>()
    private val pokeAdapter by lazy { PokeAdapter() }
    private val pokeQue by lazy { PokeQue(1,25) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPokeMonBinding.inflate(layoutInflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokeQue.let {
            pokeViewModel.fetchPokeData(it)
        }
        initViews()
    }


    private fun initViews() = with(binding) {
        rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                    Toast.makeText(context,"TOP of PAGE",Toast.LENGTH_SHORT).show()
                } else if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    pokeViewModel.fetchPokeData(PageAction.NEXT)
                }
            }
        })
    }


}