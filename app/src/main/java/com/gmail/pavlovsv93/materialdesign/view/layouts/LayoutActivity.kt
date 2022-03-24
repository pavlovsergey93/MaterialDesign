package com.gmail.pavlovsv93.materialdesign.view.layouts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityLayoutsBinding

class LayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLayoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        binding.alBottomNavigationView.selectedItemId = R.id.menu_constraint_layout

        binding.alBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_constraint_layout -> {
                    showFragment(ConstraintFragment.newInstance())
                    true
                }
                R.id.menu_constraint_layout -> {
                    true
                }
                R.id.menu_constraint_layout -> {
                    true
                }
                else -> false
            }
        }

    }

    private fun showFragment(f: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.al_fragment_container, f)
            .commit()
    }

}