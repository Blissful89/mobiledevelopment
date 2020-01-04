package com.example.hvaquest.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.hvaquest.R
import com.example.hvaquest.models.Question
import com.example.hvaquest.ui.viewmodels.QuestionsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {
    private lateinit var viewModel: QuestionsViewModel
    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar.title = getString(R.string.question_title)
            viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(QuestionsViewModel::class.java)

        question = viewModel.questions.value!![viewModel.index]

        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvQuestionTitle.text = question.question
        rbOne.text = question.choices[0]
        rbTwo.text = question.choices[1]
        rbThree.text = question.choices[2]

        tvQuestionIndex.text = getString(R.string.index, viewModel.index + 1, viewModel.size)

        btnQuestionConfirm.setOnClickListener { onConfirmPressed() }
    }

    private fun onConfirmPressed() {
        if (questionIsCorrect()) {
            findNavController().navigate(R.id.action_questionFragment_to_locationFragment)
        } else {
            Snackbar.make(view!!, "Wrong answer", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun questionIsCorrect(): Boolean {
        return when (rbAnswers.checkedRadioButtonId) {
            rbOne.id -> question.correctAnswer == rbOne.text
            rbTwo.id -> question.correctAnswer == rbTwo.text
            rbThree.id -> question.correctAnswer == rbThree.text
            else -> false
        }
    }

}
