package com.daya.android.recyclerview.sample.ui

import com.daya.android.recyclerview.EditableRecyclerViewHolder
import com.daya.android.recyclerview.sample.databinding.ItemUserBinding
import com.daya.android.recyclerview.sample.model.User

class UserRecyclerViewHolder(
    private val binding: ItemUserBinding
) : EditableRecyclerViewHolder(binding.root) {
    fun bind(user: User, isEditing: Boolean) {
        binding.user = user
        binding.isEditing = isEditing
        binding.executePendingBindings()
    }
}
