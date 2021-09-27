package com.phatherjay.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.pokedex.databinding.PokeCardBinding
import com.phatherjay.pokedex.model.Data
import com.phatherjay.pokedex.model.Pokedex
import com.phatherjay.pokedex.utils.loadWithGlide

class PokeAdapter(private val pokeList: MutableList<Data> = mutableListOf()) : RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        PokeViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.loadPokedex(pokeList[position])
    }

    fun updateList(poke: List<Data>) {
        Log.e("Update", "Update function being called")
        val positionStart = pokeList.size
        pokeList.addAll(poke)
        notifyItemRangeInserted(positionStart, poke.size)
    }

    class PokeViewHolder(
        private val binding: PokeCardBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun loadPokedex(pokePic : Data)= with(binding){
            pokePic.images?.large?.let {
                Log.e("lPd", "loadPokedex: $it", )
                ivPokePic.loadWithGlide(it) }
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