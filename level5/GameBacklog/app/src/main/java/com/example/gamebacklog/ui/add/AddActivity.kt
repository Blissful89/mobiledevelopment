package com.example.gamebacklog.ui.add

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gamebacklog.R

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

class AddActivity : AppCompatActivity() {
    private lateinit var addActivityViewModel: AddActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews(){
        fab.setOnClickListener {
            onSaveClicked()
        }
    }

    private fun initViewModel(){
        addActivityViewModel = ViewModelProviders.of(this).get(AddActivityViewModel::class.java)

        addActivityViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        })

        addActivityViewModel.success.observe(this, Observer { success ->
            if (success) finish()
        })
    }

    private fun onSaveClicked() {
        addActivityViewModel.game.value?.apply {
            title = etTitle.text.toString()
            platform = etPlatform.text.toString()
        }
        addActivityViewModel.day.value = etDay.text.toString().toInt()
        addActivityViewModel.month.value = etMonth.text.toString().toInt()
        addActivityViewModel.year.value = etYear.text.toString().toInt()

        addActivityViewModel.insertGame()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
