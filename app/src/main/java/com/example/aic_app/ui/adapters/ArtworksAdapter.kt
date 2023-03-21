package com.example.aic_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.aic_app.databinding.ArtworkItemBinding
import com.example.aic_app.ui.PREFIX
import com.example.aic_app.ui.SUFFIX
import com.example.aic_app.ui.model.Art
import com.squareup.picasso.Picasso

class ArtworksAdapter(val goToDetail: (Long) -> Unit) : ListAdapter<Art, ArtworksAdapter.ArtworkViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val binding = ArtworkItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = ArtworkViewHolder(binding)

        binding.image.setOnClickListener {
            goToDetail(currentList[holder.adapterPosition].id)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ArtworkViewHolder(private val binding: ArtworkItemBinding) : ViewHolder(binding.root) {

        fun bind(item: Art) {
            Picasso.get()
                .load(PREFIX + item.imageId + SUFFIX)
                .into(binding.image)
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Art>() {

    override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem.imageId == newItem.imageId
    }

    override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
        return oldItem == newItem
    }
}
