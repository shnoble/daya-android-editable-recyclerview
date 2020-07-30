package com.daya.android.recyclerview.sample.ui

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
import com.daya.android.recyclerview.EditableRecyclerViewHelper
import com.daya.android.recyclerview.sample.R
import com.daya.android.recyclerview.sample.model.User

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: UserRecyclerViewAdapter

    private var items = (0..100)
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
            viewAdapter.editable = true
            viewAdapter.notifyDataSetChanged()
        }
        completeButton.setOnClickListener {
            editButton.visibility = View.VISIBLE
            completeButton.visibility = View.GONE
            viewAdapter.editable = false
            viewAdapter.notifyDataSetChanged()
            if (viewAdapter.items != items) {
                items = mutableListOf<User>().apply {
                    for ((index, user) in viewAdapter.items.withIndex()) {
                        add(index, user.copy())
                    }
                }
                Toast.makeText(this.context, "Changed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(view: View, viewAdapter: UserRecyclerViewAdapter) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            // specify an viewAdapter
            adapter = viewAdapter

            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        }
        val editableRecyclerViewHelper = EditableRecyclerViewHelper(viewAdapter)
        editableRecyclerViewHelper.attachToRecyclerView(recyclerView)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}