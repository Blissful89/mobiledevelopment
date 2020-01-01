package com.example.lendahand.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lendahand.R
import com.example.lendahand.adapter.TaskAdapter
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : Fragment() {
    private val temp = Task("Task 1","12-06-2016", ArrayList(), ArrayList(),"/bestaatniet",false)
    private val tasks = arrayListOf<Task>()
    private val taskAdapter = TaskAdapter(tasks)
    private lateinit var viewModel: TasksFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(activity, 1, RecyclerView.VERTICAL, false)
        rvTasks.layoutManager = gridLayoutManager
        rvTasks.adapter = taskAdapter
        createItemTouchHelper().attachToRecyclerView(rvTasks)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksFragmentViewModel::class.java)
        viewModel.tasks.observe(this, Observer {
            tasks.clear()
            tasks.addAll(it)
            taskAdapter.notifyDataSetChanged()
        })

        viewModel.loading.observe(this, Observer {
            pbLoading.isVisible = it
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.getTasks()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                println("Swiped: $direction")
            }
        }
        return ItemTouchHelper(callback)
    }
}
