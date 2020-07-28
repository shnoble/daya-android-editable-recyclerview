package com.daya.android.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.util.Logger

abstract class EditableRecyclerViewAdapter<VH : EditableRecyclerViewHolder> :
    RecyclerView.Adapter<VH>(), ItemTouchCallback.ItemTouchContract<VH> {
    internal var startDragListener: StartDragListener? = null

    var isEditing: Boolean = false
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    internal interface StartDragListener {
        fun requestDrag(viewHolder: RecyclerView.ViewHolder)
    }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, isEditing: Boolean): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Logger.d(TAG, "onCreateViewHolder")
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.item_editable_recycler_view, parent, false)
        val contentLayout = rootView.findViewById<ViewGroup>(R.id.content_layout)
        return onCreateViewHolder(contentLayout, viewType, isEditing)
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, isEditing: Boolean)

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
        holder.isEditing = isEditing
        onBindViewHolder(holder, position, isEditing)
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