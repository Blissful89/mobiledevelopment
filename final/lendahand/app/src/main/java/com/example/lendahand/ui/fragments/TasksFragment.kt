package com.example.lendahand.ui.fragments

import android.app.Activity
import android.content.Intent
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
import com.example.lendahand.ui.activities.detail.DetailActivity
import com.example.lendahand.ui.activities.edit.EditTaskActivity
import com.example.lendahand.ui.activities.edit.FINISHED_TASK
import kotlinx.android.synthetic.main.fragment_tasks.*

const val EDIT_TASK_REQUEST_CODE = 100

class TasksFragment : Fragment() {
    private val tasks = arrayListOf<Task>()
    private val taskAdapter = TaskAdapter(tasks) { question: Task -> onTaskClicked(question) }
    private lateinit var viewModel: TasksFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabNewTask.setOnClickListener { onFabClicked() }

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

        viewModel.sync()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) onTaskSwipedLeft()
                if (direction == ItemTouchHelper.RIGHT) onTaskSwipedRight()
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun onTaskSwipedLeft() = Toast.makeText(activity, "Completed", Toast.LENGTH_SHORT).show()

    private fun onTaskSwipedRight() = Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show()

    private fun onTaskClicked(task: Task) = startActivity(Intent(activity, DetailActivity::class.java).putExtra("DETAIL", task))

    private fun onFabClicked() {
        startActivityForResult(
            Intent(activity, EditTaskActivity::class.java),
            EDIT_TASK_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                EDIT_TASK_REQUEST_CODE -> {
                    val task = data!!.getParcelableExtra<Task>(FINISHED_TASK)
                    addTask(task)
                }
            }
        }
    }

    // FIRST STORE THEN RE-RETRIEVE!! TODO
    private fun addTask(task: Task) {
        tasks.add(0, task)
        taskAdapter.notifyDataSetChanged()
    }
}
