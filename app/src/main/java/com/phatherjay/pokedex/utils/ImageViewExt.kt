package com.phatherjay.pokedex.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWithGlide(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}