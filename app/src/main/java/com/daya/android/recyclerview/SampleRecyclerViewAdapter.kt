package com.daya.android.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SampleRecyclerViewAdapter(
    items: List<String>
) : RecyclerView.Adapter<SampleRecyclerViewHolder>() {
    private val mutableItems: MutableList<String> = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleRecyclerViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return SampleRecyclerViewHolder(rootView)
    }

    override fun getItemCount() = mutableItems.size

    override fun onBindViewHolder(holder: SampleRecyclerViewHolder, position: Int) {
        holder.textView.text = mutableItems[position]
    }
}
