package com.example.lendahand.ui.activities.edit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lendahand.R
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.activity_edit_task.*

const val EDIT = "EDIT"
const val FINISHED_TASK = "FINISHED_TASK"

class EditTaskActivity : AppCompatActivity() {
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        ivTaskDetailBack.setOnClickListener { onBackPressed() }
        fabTaskCancel.setOnClickListener { onBackPressed() }
        fabTaskUpdate.setOnClickListener { onUpdatePressed() }

        initViews()
    }

    private fun initViews() {
        val task = intent.getParcelableExtra<Task>(EDIT)

        if (task != null) {
            this.task = task
            etTitle.setText(task.title)
            if (task.descriptions.isNotEmpty()) etDescOne.setText(task.descriptions[0])
            if (task.descriptions.size > 1) etDescTwo.setText(task.descriptions[1])
            if (task.descriptions.size > 2) etDescThree.setText(task.descriptions[3])
            if (task.comments.isNotEmpty()) etComOne.setText(task.comments[0])
            if (task.comments.size > 1) etComTwo.setText(task.comments[1])
            if (task.comments.size > 2) etComThree.setText(task.comments[3])
        }
    }

    private fun onUpdatePressed() {
        if (
            etTitle.text.toString().isNotBlank() &&
            etDate.text.toString().isNotBlank()
        ) {
            task = Task(
                etTitle.text.toString(),
                etDate.text.toString(),
                arrayListOf(),
                arrayListOf(),
                "/200/300",
                false
            )
            if (etDescOne.text.toString().isNotBlank()) task.descriptions += etDescOne.text.toString()
            if (etDescTwo.text.toString().isNotBlank()) task.descriptions += etDescTwo.text.toString()
            if (etDescThree.text.toString().isNotBlank()) task.descriptions += etDescThree.text.toString()
            if (etComOne.text.toString().isNotBlank()) task.comments += etComOne.text.toString()
            if (etComTwo.text.toString().isNotBlank()) task.comments += etComTwo.text.toString()
            if (etComThree.text.toString().isNotBlank()) task.comments += etComThree.text.toString()
            complete(task)
        } else Toast.makeText(this, "Incorrect input", Toast.LENGTH_SHORT).show()
    }

    private fun complete(task: Task) {
        val resultIntent = Intent()
        resultIntent.putExtra(FINISHED_TASK, task)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
