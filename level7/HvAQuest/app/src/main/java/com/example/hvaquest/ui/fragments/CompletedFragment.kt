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
import com.example.hvaquest.ui.viewmodels.QuestionsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_completed.*

class CompletedFragment : Fragment() {
    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity!!.toolbar.title = getString(R.string.quest_completed)
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuestionsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnd.setOnClickListener {
            viewModel.reset()
            findNavController().navigate(R.id.action_completedFragment_to_homeFragment)
        }
    }
}
