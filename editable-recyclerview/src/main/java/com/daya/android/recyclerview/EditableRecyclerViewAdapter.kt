package com.daya.android.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.util.Logger

abstract class EditableRecyclerViewAdapter<VH : EditableRecyclerViewHolder> :
    RecyclerView.Adapter<VH>(), ItemTouchCallback.ItemTouchContract<VH> {
    internal var startDragListener: StartDragListener? = null

    var editable: Boolean = false

    internal interface StartDragListener {
        fun requestDrag(viewHolder: RecyclerView.ViewHolder)
    }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, editable: Boolean): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Logger.d(TAG, "onCreateViewHolder")
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_editable_recycler_view, parent, false)
        val contentLayout = rootView.findViewById<ViewGroup>(R.id.content_layout)
        return onCreateViewHolder(contentLayout, viewType, editable)
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, editable: Boolean)

    override fun onBindViewHolder(holder: VH, position: Int) {
        Logger.d(TAG, "onBindViewHolder: $position")
        holder.moveView.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startDragListener?.requestDrag(holder)
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()
                }
            }
            return@setOnTouchListener false
        }
        holder.deleteView.setOnClickListener {
            onItemRemoved(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
        holder.editable = editable
        onBindViewHolder(holder, position, editable)
    }

    override fun onItemSelected(viewHolder: VH) {
        viewHolder.isSelected = true
    }

    override fun onItemClear(viewHolder: VH) {
        viewHolder.isSelected = false
    }

    abstract fun onItemRemoved(position: Int)

    companion object {
        const val TAG = "RecyclerViewAdapter"
    }
}