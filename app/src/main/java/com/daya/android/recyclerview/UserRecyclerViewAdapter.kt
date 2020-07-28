package com.daya.android.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.databinding.ItemUserBinding
import com.daya.android.recyclerview.model.User

class UserRecyclerViewAdapter(
    items: List<User>
) : RecyclerView.Adapter<UserRecyclerViewHolder>() {
    private val mutableItems: MutableList<User> by lazy {
        mutableListOf<User>().apply {
            for ((index, user) in items.withIndex()) {
                add(index, user.copy())
            }
        }
    }
    val items: List<User> = mutableItems

    var isEditing = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRecyclerViewHolder(binding)
    }

    override fun getItemCount() = mutableItems.size

    override fun onBindViewHolder(holder: UserRecyclerViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.binding.user = mutableItems[position]
        holder.binding.editing = isEditing
    }

    companion object {
        const val TAG = "UserRecyclerViewAdapter"
    }
}
