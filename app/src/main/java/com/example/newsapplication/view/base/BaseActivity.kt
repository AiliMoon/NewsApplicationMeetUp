package com.example.newsapplication.view.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.newsapplication.R
import com.example.newsapplication.model.navigation.FragmentTransaction

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var navigation: NavController

    private fun initializeNavController() {
        navigation = Navigation.findNavController(this, R.id.fragmentContainerView)
    }

    override fun onStart() {
        super.onStart()
        initializeNavController()
    }

    fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        navigation.navigate(
            fragmentTransaction.navigationId,
            fragmentTransaction.bundle,
            fragmentTransaction.navOptions
        )
    }

    fun popBackStack(@IdRes navigationId: Int, inclusive: Boolean = false) {
        navigation.popBackStack(navigationId, inclusive)
    }
}