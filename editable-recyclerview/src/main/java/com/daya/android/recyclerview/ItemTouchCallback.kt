package com.daya.android.recyclerview

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.util.Logger

internal class ItemTouchCallback<T : RecyclerView.ViewHolder>(
    private val contract: ItemTouchContract<T>
) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled() = false

    override fun isItemViewSwipeEnabled() = false

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        Logger.d(TAG, "onMove: viewHolder(${viewHolder.adapterPosition}), target(${target.adapterPosition})")
        return contract.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        Logger.d(TAG, "onSelectedChanged: viewHolder(${viewHolder?.adapterPosition}), actionState($actionState)")
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            @Suppress("UNCHECKED_CAST")
            contract.onItemSelected(viewHolder as T)
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        Log.d(TAG, "clearView: viewHolder(${viewHolder.adapterPosition})")
        @Suppress("UNCHECKED_CAST")
        contract.onItemClear(viewHolder as T)
    }

    internal interface ItemTouchContract<T : RecyclerView.ViewHolder> {
        fun onItemMoved(from: Int, to: Int): Boolean
        fun onItemSelected(viewHolder: T)
        fun onItemClear(viewHolder: T)
    }

    companion object {
        private const val TAG = "ItemTouchCallback"
    }
}