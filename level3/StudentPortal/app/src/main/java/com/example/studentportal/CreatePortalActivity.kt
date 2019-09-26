package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_portal.*

const val EXTRA_PORTAL = "EXTRA_PORTAL"

class CreatePortalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_portal)

        initViews()
    }

    private fun initViews() {
        btnAddPortal.setOnClickListener { onAddPortalClick() }
    }

    private fun onAddPortalClick() {
        when {
            tiPortalName.text.toString().isBlank() -> Snackbar.make(tiPortalName, "You must fill in a name!", Snackbar.LENGTH_SHORT).show()
            tiPortalURL.text.toString().isBlank() -> Snackbar.make(tiPortalName, "You must fill in a url!", Snackbar.LENGTH_SHORT).show()
            !tiPortalURL.text.toString().contains("http") -> Snackbar.make(tiPortalName, "Fill in a valid url!", Snackbar.LENGTH_SHORT).show()
            else -> {
                val portal = Portal(tiPortalName.text.toString(),tiPortalURL.text.toString())
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_PORTAL,portal)
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
            }
        }
    }
}
