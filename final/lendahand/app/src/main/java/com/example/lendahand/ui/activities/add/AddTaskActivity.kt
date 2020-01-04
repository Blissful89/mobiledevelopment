package com.example.lendahand.ui.activities.add

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lendahand.R
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.activity_add_task.*

const val CREATED_TASK = "CREATED_TASK"

class AddTaskActivity : AppCompatActivity() {
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        initViews()
    }

    private fun initViews() {
        ivTaskDetailBack.setOnClickListener { onBackPressed() }
        fabTaskCancel.setOnClickListener { onBackPressed() }
        fabTaskUpdate.setOnClickListener { onUpdatePressed() }
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
        resultIntent.putExtra(CREATED_TASK, task)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
