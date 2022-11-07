package com.example.homeworks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import com.example.homeworks.databinding.DialogItemBinding
import com.example.homeworks.databinding.FragmentDialogBinding
import com.example.homeworks.models.DialogItem

class DialogItemsAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<DialogItemsAdapter.ItemsViewHolder>() {

    private val items = listOf(
        DialogItem(
            R.drawable.ic_baseline_brightness_1_24,
            "Go to A3",
            R.id.action_bottomDialogFragment_to_a3Fragment
        ),
        DialogItem(
            R.drawable.ic_baseline_brightness_2_24,
            "Go to B2",
            R.id.action_bottomDialogFragment_to_b2Fragment
        ),
        DialogItem(
            R.drawable.ic_baseline_brightness_3_24,
            "Go to C2",
            R.id.action_bottomDialogFragment_to_c2Fragment
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.dialog_item, parent, false)
        return ItemsViewHolder(DialogItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    inner class ItemsViewHolder(private val itemBinding: DialogItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: DialogItem) {
            with(itemBinding) {
                iv.setImageResource(item.img)
                tv.text = item.label
                root.setOnClickListener {
                    fragment.findNavController().navigate(items[position].action)
                }
            }
        }
    }
}
