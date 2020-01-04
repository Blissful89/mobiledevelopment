package com.example.lendahand.ui.activities.add

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lendahand.R
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.activity_add_task.*
import java.util.*

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
        btnDatePicker.setOnClickListener { onBtnDatePickerPressed() }
    }

    /* Date picker calls back setDate which sets the tvDateFromPicker which is tested upon completion */
    private fun onBtnDatePickerPressed() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                setDate(
                    mYear, mMonth, mDay
                )
            },
            year,
            month,
            day
        )

        datePicker.show()
    }

    private fun setDate(mYear: Int, mMonth: Int, mDay: Int) {
        tvDateFromPicker.text = stringifyDate(mYear, mMonth, mDay)
    }

    private fun stringifyDate(year: Int, month: Int, day: Int): String {
        val mDay = if (day < 10) "0$day" else "$day"
        val mMonth = if (month < 10) "0${month + 1}" else "${month + 1}"

        return "$mDay-$mMonth-$year"
    }

    /* Checks all input fields for input */
    private fun onUpdatePressed() {
        if (
            etTitle.text.toString().isNotBlank() &&
            tvDateFromPicker.text.isNotBlank()
        ) {
            task = Task(
                etTitle.text.toString(),
                tvDateFromPicker.text.toString(),
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
        } else Toast.makeText(this, "Please fill in a title and date", Toast.LENGTH_SHORT).show()
    }

    private fun complete(task: Task) {
        val resultIntent = Intent()
        resultIntent.putExtra(CREATED_TASK, task)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
