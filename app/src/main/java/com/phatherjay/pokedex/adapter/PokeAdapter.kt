package com.phatherjay.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.phatherjay.pokedex.databinding.PokeCardBinding
import com.phatherjay.pokedex.model.Data
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

    fun clear() {
        val listSize = pokeList.size
        pokeList.clear()
        notifyItemRangeRemoved(0, listSize)
    }

    class PokeViewHolder(
        private val binding: PokeCardBinding
    ) : RecyclerView.ViewHolder(binding.root){

        fun loadPokedex(pokePic : Data)= with(binding){
            pokePic.images?.large?.let {
                ivPokePic.loadWithGlide(it)
            }
        }


        fun loadFavs(favs: Data)= with(binding){
            cbPokeBall.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Log.e("FAVORITES", "loadPokedex: ${favs.name} is checked")
                    favs.images?.large?.let {
                        ivPokePic.loadWithGlide(it)
                    }
                }
                else {
                    Log.e("FAVORITES", "loadPokedex: ${favs.name} is unchecked" )
                }
            }
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