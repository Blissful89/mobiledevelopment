package com.example.logica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val table = Table(
            Row(zeroZero.text.toString(), oneZero.text.toString()),
            Row(zeroOne.text.toString(), oneOne.text.toString()),
            Row(zeroTwo.text.toString(), oneTwo.text.toString()),
            Row(zeroThree.text.toString(), oneThree.text.toString())
        )

        btnSubmit.setOnClickListener {
            if (checkAnswers(table)) showResult(getString(R.string.correct)) else showResult(getString(R.string.incorrect))
        }
    }

    private fun checkAnswers(table: Table): Boolean {
        val answerOneCorrect = compareLine(answerOne.text.toString(), table.rowOne)
        val answerTwoCorrect = compareLine(answerTwo.text.toString(), table.rowTwo)
        val answerThreeCorrect = compareLine(answerThree.text.toString(), table.rowThree)
        val answerFourCorrect = compareLine(answerFour.text.toString(), table.rowFour)

        return (answerOneCorrect && answerTwoCorrect && answerThreeCorrect && answerFourCorrect)
    }

    private fun compareLine(answer: String, line: Row): Boolean {
        val letterT = getString(R.string.T)
        val letterF = getString(R.string.F)
        return when (answer) {
            letterT -> (line.left == letterT && line.right == letterT)
            letterF -> (line.left != line.right || (line.right == letterF && line.left == letterF))
            else -> false
        }
    }

    private fun showResult(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
