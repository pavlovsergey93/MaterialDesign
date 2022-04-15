package com.gmail.pavlovsv93.materialdesign.ui.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityLayoutsBinding
import com.gmail.pavlovsv93.materialdesign.ui.layout.fragment.ConstraintFragment
import com.gmail.pavlovsv93.materialdesign.ui.layout.fragment.CoordinatorFragment

class LayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)



        binding.alBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_constraint_layout -> {
                    showFragment(ConstraintFragment.newInstance())
                    true
                }
                R.id.menu_coordinator_layout -> {
                    showFragment(CoordinatorFragment.newInstance())
                    true
                }
                R.id.menu_motion_layout -> {

                    true
                }
                else -> false
            }
        }
        binding.alBottomNavigationView.selectedItemId = R.id.menu_constraint_layout
    }

    private fun showFragment(f: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.al_fragment_container, f)
            .commit()
    }

}