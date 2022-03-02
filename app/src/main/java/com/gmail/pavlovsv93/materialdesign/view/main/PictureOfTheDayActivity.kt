package com.gmail.pavlovsv93.materialdesign.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityMainBinding
import com.gmail.pavlovsv93.materialdesign.utils.BACKSTACK
import com.gmail.pavlovsv93.materialdesign.utils.NO_BACKSTACK
import com.gmail.pavlovsv93.materialdesign.utils.TAG_BS
import com.gmail.pavlovsv93.materialdesign.utils.TAG_MA
import com.gmail.pavlovsv93.materialdesign.view.bottomsheet.BottomNavigationFragment
import com.gmail.pavlovsv93.materialdesign.view.menu.navigation.ChipsFragment
import com.google.android.material.bottomappbar.BottomAppBar

class PictureOfTheDayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFragment(PictureOfTheDayFragment.newInstance(), NO_BACKSTACK)
        }

        this.setSupportActionBar(binding.aBottomAppBar)
        clickedOnFAB()
    }

    private var isMain: Boolean = true

    private fun clickedOnFAB() {
        binding.aFab.setOnClickListener {
            if (isMain) {
                binding.aBottomAppBar.navigationIcon = null
                binding.aBottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.aFab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_arrow_back_24
                    )
                )
                binding.aBottomAppBar.replaceMenu(R.menu.menu_other_bottom_app_bar)
            } else {
                binding.aBottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(this, R.drawable.ic_round_dehaze_24)
                binding.aBottomAppBar.replaceMenu(R.menu.menu_bottom_app_bar)
                binding.aFab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_baseline_add_24
                    )
                )
                binding.aBottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
            isMain = !isMain
        }
    }

    private fun showFragment(fragment: Fragment, backstack: Boolean) {
        val sfm = supportFragmentManager.beginTransaction()
            .replace(R.id.a_frame_container, fragment)

        // Проверка необходимости положить предыдущий фрагмент в бэкстэк
        if (backstack) {
            sfm.addToBackStack(fragment.toString())
        }
        sfm.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_bab_setting -> {
                Log.d(TAG_MA, "menu Setting")
                showFragment(ChipsFragment.newInstance(), BACKSTACK)
            }
            R.id.menu_bab_fav -> {
                Log.d(TAG_MA, "menu Favourite")
            }
            android.R.id.home -> {
                BottomNavigationFragment().show(supportFragmentManager, "")
                Log.d(TAG_MA, "burger")
            }

        }
        return super.onOptionsItemSelected(item)
    }
}