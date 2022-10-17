package com.example.homeworks.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworks.Models.PlantModel
import com.example.homeworks.R
import com.example.homeworks.databinding.ItemPlantBinding

class PlantsAdapter() :
    RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>() {

    var items: List<PlantModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClickListener: ((PlantModel) -> Unit)? = null
    var indent: Int = 0
    var colorOnVisit: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(ItemPlantBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    inner class PlantViewHolder(private val itemPlantBinding: ItemPlantBinding) :
        RecyclerView.ViewHolder(itemPlantBinding.root) {

        init {
            with(itemPlantBinding) {
                root.setOnClickListener {
                    items[adapterPosition].visited = true
                    onItemClickListener?.invoke(items[adapterPosition])
                }
            }
        }

        fun bind(model: PlantModel) {
            with(itemPlantBinding) {
                if (adapterPosition == 0) {
                    val lp = root.layoutParams as ViewGroup.MarginLayoutParams
                    lp.topMargin = indent
                }
                if (model.visited) {
                    colorOnVisit?.let {
                        root.setCardBackgroundColor(it)
                    }
                    icVisited.visibility = View.VISIBLE
                }

                tvPlantName.setText(model.plantName)
                tvBotanicalName.setText(model.botanicalName)
                tvPlantType.setText(model.plantType)
                Glide.with(itemPlantBinding.root)
                    .load(model.imgUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(itemPlantBinding.ivPhotoPreview);
            }
        }
    }
}
