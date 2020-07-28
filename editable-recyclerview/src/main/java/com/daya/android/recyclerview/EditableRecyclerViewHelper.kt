package com.daya.android.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class EditableRecyclerViewHelper<VH : EditableRecyclerViewHolder>(
    private val viewAdapter: EditableRecyclerViewAdapter<VH>
) : EditableRecyclerViewAdapter.StartDragListener {
    private var itemTouchHelper: ItemTouchHelper? = null

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        viewAdapter.startDragListener = this
        itemTouchHelper = ItemTouchHelper(ItemTouchCallback(viewAdapter))
    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper?.startDrag(viewHolder)
    }
}

