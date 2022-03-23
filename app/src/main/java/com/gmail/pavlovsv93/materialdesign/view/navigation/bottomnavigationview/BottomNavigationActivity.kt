package com.gmail.pavlovsv93.materialdesign.view.navigation.bottomnavigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityBottomNavigationBinding
import com.gmail.pavlovsv93.materialdesign.model.theme.ThemeStorage
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.EarthFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.MarsFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.SolarSystemFragment
import com.google.android.material.badge.BadgeDrawable.TOP_START

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    private val themeStorage: ThemeStorage = ThemeStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(themeStorage.getTheme().theme)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.abnBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_earth -> {
                    showFragment(EarthFragment.newInstance())
                    true
                }
                R.id.menu_mars -> {
                    showFragment(MarsFragment.newInstance())
                    binding.abnBottomNavigationView.removeBadge(R.id.menu_mars) // todo удаление бейджа
                    true
                }
                R.id.menu_solar_system -> {
                    showFragment(SolarSystemFragment.newInstance())
                    true
                }
                else -> false
            }
        }
        // todo по умолчанию задать активное меню
        binding.abnBottomNavigationView.selectedItemId = R.id.menu_mars

        // todo добавление бейджа и задаем значение
        val badge = binding.abnBottomNavigationView.getOrCreateBadge(R.id.menu_mars)
        badge?.number = 999                 // todo задать количественное значение бейджа
        badge.maxCharacterCount = 3         // todo задать максимальное количество выводимых знаков
        badge.badgeGravity = TOP_START      // todo задать расположение бейджа относитень ItemBottomNavigationView

    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.abn_fragment_container, fragment)
            .commit()

    }
}