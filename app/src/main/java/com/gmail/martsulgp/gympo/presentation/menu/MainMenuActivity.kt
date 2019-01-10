package com.gmail.martsulgp.gympo.presentation.menu

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.presentation.menu.gymsMapMenu.GymsMapFragment
import com.gmail.martsulgp.gympo.presentation.menu.profileMenu.ProfileFragment
import com.gmail.martsulgp.gympo.presentation.menu.settingsMenu.SettingsFragment
import com.gmail.martsulgp.gympo.presentation.menu.statisticMenu.StatisticFragment
import com.gmail.martsulgp.gympo.presentation.menu.trainingsMenu.TrainingsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*

class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var userName: TextView
    lateinit var userEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val headerView = nav_view.getHeaderView(0)
        userName = headerView.findViewById<TextView>(R.id.user_name).apply{ text = UserDataObj.name }
        userEmail = headerView.findViewById<TextView>(R.id.user_email).apply{ text = UserDataObj.email }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_exercise -> {
                toolbar.title = "Exercises"
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, TrainingsFragment.newInstance()).commit()
            }
            R.id.nav_settings -> {
                toolbar.title = "Settings"
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, SettingsFragment()).commit()
            }
            R.id.nav_statistics -> {
                toolbar.title = "Statistics"
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, StatisticFragment()).commit()
            }
            R.id.nav_profile -> {
                toolbar.title = "Profile"
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, ProfileFragment()).commit()
            }
            R.id.nav_map -> {
                toolbar.title = "Map"
                fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_fragment_container, GymsMapFragment()).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
