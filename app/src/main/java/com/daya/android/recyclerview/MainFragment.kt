package com.daya.android.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daya.android.recyclerview.model.User

class MainFragment : Fragment() {
    private lateinit var viewAdapter: UserRecyclerViewAdapter

    private val items = (0..100)
        .asSequence().map { User("Jack-$it", 10, "") }.toList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView?.let {
            viewAdapter = UserRecyclerViewAdapter(items.toMutableList())
            setupRecyclerView(it, viewAdapter)
            setupMenu(it)
        }
        return rootView
    }

    private fun setupMenu(view: View) {
        val editButton = view.findViewById<Button>(R.id.edit_button)
        val completeButton = view.findViewById<Button>(R.id.complete_button)
        editButton.setOnClickListener {
            editButton.visibility = View.GONE
            completeButton.visibility = View.VISIBLE
            viewAdapter.isEditing = true
        }
        completeButton.setOnClickListener {
            editButton.visibility = View.VISIBLE
            completeButton.visibility = View.GONE
            viewAdapter.isEditing = false
            if (viewAdapter.items != items) {
                Toast.makeText(this.context, "Changed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(view: View, viewAdapter: UserRecyclerViewAdapter) {
        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            // specify an viewAdapter
            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}