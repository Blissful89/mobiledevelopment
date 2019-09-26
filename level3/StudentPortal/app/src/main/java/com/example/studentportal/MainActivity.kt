package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.GridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_REQUEST_CODE = 1337

class MainActivity : AppCompatActivity() {
    private val portals = Portal.defaultPortals
    private val portalAdapter = PortalAdapter(portals) { portal: Portal -> onPortalClicked(portal) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { startAddActivity() }

        initViews()
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(this,2)
        rvPortals.adapter = portalAdapter
    }

    private fun startAddActivity(){
        val intent = Intent(this,CreatePortalActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    addPortal(portal)
                }
            }
        }
    }

    private fun addPortal(portal: Portal?) {
        portals.add(portal)
        portalAdapter.notifyDataSetChanged()
    }

    private fun onPortalClicked(portal: Portal) {
        val builder = CustomTabsIntent.Builder()
        val intent = builder.build()
        intent.launchUrl(this, Uri.parse(portal.url))
    }
}
