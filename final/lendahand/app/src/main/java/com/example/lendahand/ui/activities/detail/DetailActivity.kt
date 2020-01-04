package com.example.lendahand.ui.activities.detail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.lendahand.R
import com.example.lendahand.adapter.ContentAdapter
import com.example.lendahand.model.Task
import kotlinx.android.synthetic.main.activity_detail.*

const val DETAIL = "DETAIL"

class DetailActivity : AppCompatActivity() {
    private val descriptions = arrayListOf<String>()
    private val comments = arrayListOf<String>()
    private lateinit var task: Task

    private val descriptionAdapter = ContentAdapter(descriptions)
    private val commentsAdapter = ContentAdapter(comments)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ivTaskDetailBack.setOnClickListener { onBackPressed() }
        fabTaskCancel.setOnClickListener { onBackPressed() }
        fabTaskComplete.setOnClickListener { onCompletePressed() }

        initViews()
    }

    private fun initViews() {
        task = intent.getParcelableExtra(DETAIL)!!
        descriptions.addAll(task.descriptions)
        comments.addAll(task.comments)

        tvTaskDetailTitle.text = task.title

        //  Skipping caching otherwise same image appears
        Glide.with(this)
            .load(task.getTaskImage())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(ivTaskDetailImage)

        displayContentFor(rvTaskDescription, Mode.DESCRIPTIONS.string)
        displayContentFor(rvTaskComments, Mode.COMMENTS.string)
    }

    private fun onCompletePressed() {
        val resultIntent = Intent()
        resultIntent.putExtra(DETAIL, task)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun displayContentFor(recyclerView: RecyclerView, type: String) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        when (type) {
            Mode.DESCRIPTIONS.string -> {
                recyclerView.adapter = descriptionAdapter
                descriptionAdapter.notifyDataSetChanged()
            }
            Mode.COMMENTS.string -> {
                recyclerView.adapter = commentsAdapter
                commentsAdapter.notifyDataSetChanged()
            }
        }
    } companion object {
        enum class Mode(val string: String){
            DESCRIPTIONS("DESCRIPTIONS"),
            COMMENTS("COMMENTS")
        }
    }
}
