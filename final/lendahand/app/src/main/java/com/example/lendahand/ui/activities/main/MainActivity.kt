package com.example.lendahand.ui.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.example.lendahand.R
import com.example.lendahand.ui.activities.login.LoginActivity
import com.example.lendahand.ui.fragments.TasksFragment
import com.example.lendahand.ui.viewmodels.TaskViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

const val HOME = "HOME"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra(HOME)

        navigationView = nav_view
        navigationView.setNavigationItemSelectedListener(this)

        drawer = drawer_layout
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_tasks -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    TasksFragment()
                ).commit()
                drawer.closeDrawer(GravityCompat.START)
            }
            R.id.nav_logout -> startActivity(Intent(this, LoginActivity::class.java))

            else -> println("Not implemented!")
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }
}
