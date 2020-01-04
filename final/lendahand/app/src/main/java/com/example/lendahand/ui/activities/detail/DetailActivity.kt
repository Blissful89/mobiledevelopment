package com.example.lendahand.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lendahand.R
import com.example.lendahand.adapter.ContentAdapter
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.activity_detail.*

const val DETAIL = "DETAIL"

private const val DESCRIPTIONS = "DESCRIPTIONS"
private const val COMMENTS = "COMMENTS"

class DetailActivity : AppCompatActivity() {
    private val descriptions = arrayListOf<String>()
    private val comments = arrayListOf<String>()

    private val descriptionAdapter = ContentAdapter(descriptions)
    private val commentsAdapter = ContentAdapter(comments)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ivTaskDetailBack.setOnClickListener { onBackPressed() }

        initViews()
    }

    private fun initViews() {
        val task = intent.getParcelableExtra<Task>(DETAIL)

        if (task != null) {
            descriptions.addAll(task.descriptions)
            comments.addAll(task.comments)
            tvTaskDetailTitle.text = task.title
            Glide.with(this).load(task.getTaskImage()).into(ivTaskDetailImage)
        }

        displayContentFor(rvTaskDescription, DESCRIPTIONS)
        displayContentFor(rvTaskComments, COMMENTS)
    }

    private fun displayContentFor(recyclerView: RecyclerView, type: String) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        when (type) {
            DESCRIPTIONS -> {
                recyclerView.adapter = descriptionAdapter
                descriptionAdapter.notifyDataSetChanged()
            }
            COMMENTS -> {
                recyclerView.adapter = commentsAdapter
                commentsAdapter.notifyDataSetChanged()
            }
        }
    }
}
