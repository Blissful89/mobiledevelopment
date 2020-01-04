package com.example.hvaquest.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.R
import com.example.hvaquest.models.Question
import com.example.hvaquest.ui.viewmodels.QuestionsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : Fragment() {
    private lateinit var viewModel: QuestionsViewModel
    private lateinit var question: Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar.title = getString(R.string.location_clue)
            viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(QuestionsViewModel::class.java)

        question = viewModel.questions.value!![viewModel.index]
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = question.clue
        ivLocation.setImageDrawable(ContextCompat.getDrawable(context!!, image))

        btnNextQuestion.setOnClickListener {
            viewModel.nextQuestion()
            if (viewModel.lastQuestion) findNavController().navigate(R.id.action_locationFragment_to_completedFragment)
            else findNavController().navigate(R.id.action_locationFragment_to_questionFragment)
        }
    }
}
