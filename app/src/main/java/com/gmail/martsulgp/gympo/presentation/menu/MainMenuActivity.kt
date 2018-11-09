package com.gmail.martsulgp.gympo.presentation.menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.presentation.menu.ExerciseMenu.ExerciseFragment
import com.gmail.martsulgp.gympo.presentation.menu.GymsMapMenu.GymsMapFragment
import com.gmail.martsulgp.gympo.presentation.menu.ProfileMenu.ProfileFragment
import com.gmail.martsulgp.gympo.presentation.menu.SettingsMenu.SettingsFragment
import com.gmail.martsulgp.gympo.presentation.menu.StatisticMenu.StatisticFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_exercise -> {
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, ExerciseFragment()).commit()
            }
            R.id.nav_settings -> {
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, SettingsFragment()).commit()
            }
            R.id.nav_statistics -> {
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, StatisticFragment()).commit()
            }
            R.id.nav_profile -> {
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, ProfileFragment()).commit()
            }
            R.id.nav_map -> {
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, GymsMapFragment()).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
