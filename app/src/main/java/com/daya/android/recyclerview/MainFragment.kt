package com.daya.android.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    private val items = (0..100)
        .asSequence().map { "Text: $it" }.toList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView?.let { setupRecyclerView(rootView) }
        return rootView
    }

    private fun setupRecyclerView(rootView: View) {
        rootView.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            // specify an viewAdapter
            adapter = SampleRecyclerViewAdapter(items.toMutableList())

            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}