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
        val table = Table(
            Row(zeroZero.text.toString(), oneZero.text.toString()),
            Row(zeroOne.text.toString(), oneOne.text.toString()),
            Row(zeroTwo.text.toString(), oneTwo.text.toString()),
            Row(zeroThree.text.toString(), oneThree.text.toString())
        )

        btnSubmit.setOnClickListener {
            if (checkAnswers(table))
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
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
        return when (answer) {
            T -> (line.left == T && line.right == T)
            F -> (line.left != line.right || (line.right == F && line.left == F))
            else -> false
        }
    }
}

private class Row(val left: String, val right: String)

private class Table(val rowOne: Row, val rowTwo: Row, val rowThree: Row, val rowFour: Row)
