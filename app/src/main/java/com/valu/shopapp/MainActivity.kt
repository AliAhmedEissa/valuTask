package com.valu.shopapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.valu.shopapp.databinding.ActivityMainBinding
import com.valu.shopapp.utils.ViewsManager

class MainActivity : AppCompatActivity(), ViewsManager {
    //  private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigationComponent()
    }

    private fun initNavigationComponent() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        navController.setGraph(R.navigation.nav_graph, intent.extras)
        setupActionBarWithNavController(navController)

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun hideToolbar() {
        binding.toolbar.visibility = View.GONE
    }

    override fun showToolbar() {
        binding.toolbar.visibility = View.VISIBLE
    }

    override fun setToolbarTitle(title:String) {
        binding.toolbar.title = title
    }

    override fun showProgressBar() {
         binding.loadingView.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
          binding.loadingView.visibility = View.GONE
    }

}