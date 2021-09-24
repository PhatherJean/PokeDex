package com.phatherjay.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.pokedex.databinding.PokeCardBinding
import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.utils.loadWithGlide

class PokeAdapter(private val pokeList: MutableList<Pokedex> = mutableListOf()) : RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        PokeViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.loadPokedex(pokeList[position])
    }

    class PokeViewHolder(
        private val binding: PokeCardBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun loadPokedex(pokedex: Pokedex)= with(binding){
            pokedex.data?.images?.small?.let { ivPokePic.loadWithGlide(it) }
        }


        companion object{
            fun getInstance(parent: ViewGroup): PokeViewHolder {
                val binding = PokeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PokeViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = pokeList.size
}