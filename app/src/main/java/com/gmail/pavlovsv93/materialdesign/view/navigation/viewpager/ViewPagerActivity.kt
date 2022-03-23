package com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepositoryInterface
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityViewPagerBinding
import com.gmail.pavlovsv93.materialdesign.model.theme.ThemeStorage


class ViewPagerActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewPagerBinding
    private val repo: InMemoryRepositoryInterface = InMemoryRepository()
    private val themeStorage = ThemeStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(themeStorage.getTheme().theme)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.avp_container, ViewPagerFragment.newInstance())
            .commit()
    }
}