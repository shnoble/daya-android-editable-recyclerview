package com.daya.android.recyclerview.sample.ui

import com.daya.android.recyclerview.EditableRecyclerViewHolder
import com.daya.android.recyclerview.sample.databinding.ItemUserBinding
import com.daya.android.recyclerview.sample.model.User

class UserRecyclerViewHolder(
    private val binding: ItemUserBinding
) : EditableRecyclerViewHolder(binding.root) {
    fun bind(user: User, editable: Boolean) {
        binding.user = user
        binding.editable = editable
        binding.executePendingBindings()
    }
}
