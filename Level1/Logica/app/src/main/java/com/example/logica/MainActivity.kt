package com.example.logica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private const val T = "T"
private const val F = "F"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            if(checkAnswers()) Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAnswers(): Boolean {
        val answerOneCorrect =
            compareLine(answerOne.text.toString(), Line(zeroZero.text.toString(), oneZero.text.toString()))
        val answerTwoCorrect =
            compareLine(answerTwo.text.toString(), Line(zeroOne.text.toString(), oneOne.text.toString()))
        val answerThreeCorrect =
            compareLine(answerThree.text.toString(), Line(zeroTwo.text.toString(), oneTwo.text.toString()))
        val answerFourCorrect =
            compareLine(answerFour.text.toString(), Line(zeroThree.text.toString(), oneThree.text.toString()))

        return (answerOneCorrect && answerTwoCorrect && answerThreeCorrect && answerFourCorrect)
    }

    private fun compareLine(answer: String, line: Line): Boolean {
        return when (answer) {
            T -> (line.left == T && line.right == T)
            F -> (line.left != line.right || (line.right == F && line.left == F))
            else -> false
        }
    }
}

private class Line(val left: String, val right: String)

