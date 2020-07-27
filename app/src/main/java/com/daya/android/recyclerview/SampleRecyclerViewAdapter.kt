package com.daya.android.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.databinding.ItemSampleBinding

class SampleRecyclerViewAdapter(
    items: List<String>
) : RecyclerView.Adapter<SampleRecyclerViewHolder>() {
    private val mutableItems: MutableList<String> = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleRecyclerViewHolder {
        val binding = ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SampleRecyclerViewHolder(binding)
    }

    override fun getItemCount() = mutableItems.size

    override fun onBindViewHolder(holder: SampleRecyclerViewHolder, position: Int) {
        holder.binding.text = mutableItems[position]
    }
}
