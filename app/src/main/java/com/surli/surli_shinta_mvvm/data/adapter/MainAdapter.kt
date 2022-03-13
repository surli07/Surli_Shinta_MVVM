package com.surli.surli_shinta_mvvm.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surli.surli_shinta_mvvm.data.model.Dog
import com.surli.surli_shinta_mvvm.databinding.ItemDogBinding

class MainAdapter(
    var dogs: List<Dog>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogs[position]
        Glide.with(holder.binding.imgDog.context)
            .load(dog.url)
            .into(holder.binding.imgDog)
    }

    override fun getItemCount() = dogs.size
}