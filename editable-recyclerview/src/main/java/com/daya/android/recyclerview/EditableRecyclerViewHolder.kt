package com.daya.android.recyclerview

import android.os.Build
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class EditableRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val blankLeft = itemView.findViewById<View>(R.id.blank_left)
    private val blankRight = itemView.findViewById<View>(R.id.blank_right)
    internal val deleteView = itemView.findViewById<View>(R.id.delete_view)
    internal val moveView = itemView.findViewById<View>(R.id.move_view)

    internal var isEditing = false
        set(value) {
            field = value
            if (value) {
                blankLeft.visibility = View.VISIBLE
                blankRight.visibility = View.VISIBLE
                deleteView.visibility = View.VISIBLE
                moveView.visibility = View.VISIBLE
            } else {
                blankLeft.visibility = View.GONE
                blankRight.visibility = View.GONE
                deleteView.visibility = View.GONE
                moveView.visibility = View.GONE
            }
        }

    internal var isSelected = false
        set(value) {
            field = value
            if (value) {
                itemView.alpha = 0.9f
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val scale = itemView.context.resources.displayMetrics.density
                    val elevation = 10 * scale + 0.5f
                    val translationZ = 25 * scale + 0.5f
                    itemView.elevation = elevation
                    itemView.translationZ = translationZ
                }
            } else {
                itemView.alpha = 1.0f
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    itemView.elevation = 0.0f
                    itemView.translationZ = 0.0f
                }
            }
        }
}