package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipequiz.QuestionList.Companion.QUESTIONS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questionAdapter = QuestionAdapter(QUESTIONS) { question: Question -> itemClicked(question) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val question = QUESTIONS[position]

                itemSwiped(direction, question)
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun itemClicked(question: Question) = displayMessage(getString(R.string.sneaky) + question.answer)

    private fun itemSwiped(direction: Int, question: Question) {
        if (checkAnswerSwipeCorrect(direction, question)) displayMessage(getString(R.string.correct) + question.answer)
        else displayMessage(getString(R.string.incorrect) + question.answer)
    }

    private fun displayMessage(text: String) = Snackbar.make(rvQuestions, text, Snackbar.LENGTH_SHORT).show()

    private fun checkAnswerSwipeCorrect(direction: Int, question: Question): Boolean {
        return (direction == ItemTouchHelper.LEFT && question.answer || direction == ItemTouchHelper.RIGHT && !question.answer)
    }
}
