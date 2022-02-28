package com.gmail.pavlovsv93.materialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            showFragment(PictureOfTheDayFragment.newInstance(), false)
        }
    }

    private fun showFragment(fragment: Fragment, backstack: Boolean) {
        val sfm = supportFragmentManager.beginTransaction()
            .replace(R.id.mframe_container, fragment)

        // Проверка необходимости положить предыдущий фрагмент в бэкстэк
        if (backstack) {
            sfm.addToBackStack(fragment.toString())
        }
        sfm.commit()
    }
}