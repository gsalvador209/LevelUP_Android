package com.tanucode.levelup.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.tanucode.levelup.R
import com.tanucode.levelup.databinding.ActivityMainBinding
import com.tanucode.levelup.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    private val filledIcons = mapOf(
        R.id.spaceFragment  to R.drawable.ic_spa_fill,
        R.id.statsFragment   to R.drawable.ic_stats_filled,
        R.id.tasksFragment to R.drawable.ic_check_circle_fill,
        R.id.calendarFragment to R.drawable.ic_calendar_fill
    )

    private val normalIcons = mapOf(
        R.id.spaceFragment to R.drawable.ic_spa,
        R.id.statsFragment to R.drawable.ic_stats,
        R.id.tasksFragment to R.drawable.ic_check_circle,
        R.id.calendarFragment to R.drawable.ic_calendar
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //Bottom nav
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_UNLABELED


        navController.addOnDestinationChangedListener { _,destination,_ ->
            updateMenuIcons(destination.id)
        }

        //Fab
        binding.fabAdd.setOnClickListener {
            AddTaskBottomSheetFragment().show(supportFragmentManager, "AddTaskDialog")
        }

        binding.ivProfileAvatar.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun updateMenuIcons(selectedItemId: Int){
        //Los actualiza en función del elemento actual
        val menu = binding.bottomNavigation.menu
        for (i in 0 until menu.size){ //Recorre todos los elementos del menu
            val menuItem = menu.getItem(i)
            val iconRes = if (menuItem.itemId == selectedItemId) { //Es el selected
                filledIcons[menuItem.itemId]
            }else{
                normalIcons[menuItem.itemId]
            }
            iconRes?.let {menuItem.setIcon(it)}

        }
    }

}