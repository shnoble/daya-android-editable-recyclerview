package com.daya.android.recyclerview.sample.binding

import android.view.View
import androidx.databinding.BindingConversion

object BindingConversions {
    @BindingConversion
    @JvmStatic
    fun booleanToVisibility(isVisible: Boolean?): Int {
        return if (isVisible == true) View.VISIBLE else View.GONE
    }
}

