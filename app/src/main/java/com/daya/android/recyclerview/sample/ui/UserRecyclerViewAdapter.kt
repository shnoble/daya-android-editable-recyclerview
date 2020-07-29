package com.daya.android.recyclerview.sample.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daya.android.recyclerview.EditableRecyclerViewAdapter
import com.daya.android.recyclerview.sample.databinding.ItemUserBinding
import com.daya.android.recyclerview.sample.model.User
import java.util.*

class UserRecyclerViewAdapter(
    items: List<User>
) : EditableRecyclerViewAdapter<UserRecyclerViewHolder>() {
    private val mutableItems: MutableList<User> by lazy {
        mutableListOf<User>().apply {
            for ((index, user) in items.withIndex()) {
                add(index, user.copy())
            }
        }
    }
    val items: List<User> = mutableItems

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
        isEditing: Boolean
    ): UserRecyclerViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, true)
        return UserRecyclerViewHolder(binding).apply {
            setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount() = mutableItems.size

    override fun onBindViewHolder(
        holder: UserRecyclerViewHolder,
        position: Int,
        isEditing: Boolean
    ) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.binding.user = mutableItems[position]
        holder.binding.isEditing = isEditing
        holder.binding.executePendingBindings()
    }

    override fun onItemMoved(from: Int, to: Int) {
        if (from < to) {
            for (position in from until to) {
                Collections.swap(mutableItems, position, position + 1)
            }
        } else {
            for (position in from downTo to + 1) {
                Collections.swap(mutableItems, position, position - 1)
            }
        }
        notifyItemMoved(from, to)
    }

    override fun onItemRemoved(position: Int) {
        mutableItems.removeAt(position)
    }

    companion object {
        const val TAG = "UserRecyclerViewAdapter"
    }
}
