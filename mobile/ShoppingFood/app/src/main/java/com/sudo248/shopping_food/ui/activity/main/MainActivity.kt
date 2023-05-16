package com.sudo248.shopping_food.ui.activity.main

import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sudo248.base_android.base.BaseActivity
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.utils.SharedPreferenceUtils
import com.sudo248.shopping_food.R
import com.sudo248.shopping_food.databinding.ActivityMainBinding
import com.sudo248.shopping_food.domain.Constant
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    private val listFragmentHideBottomNav = listOf(
        R.id.stepFragment,
        R.id.foodDetailFragment
    )

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id in listFragmentHideBottomNav) {
            goneBottomNav()
        } else {
            showBottomNav()
        }
    }

    override fun initView() {
        SharedPreferenceUtils.putBoolean(Constant.IS_FIRST_OPEN_APP, false)
        setupBottomNav()
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    private fun setupBottomNav() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHost.navController
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )
    }

    private fun goneBottomNav() {
        if (binding.bottomNav.isVisible) {
            binding.bottomNav.gone()
        }
    }

    private fun showBottomNav() {
        if (binding.bottomNav.isGone) {
            binding.bottomNav.visible()
        }
    }
}