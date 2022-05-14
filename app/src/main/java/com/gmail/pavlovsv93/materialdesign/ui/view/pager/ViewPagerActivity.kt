package com.gmail.pavlovsv93.materialdesign.ui.view.pager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.data.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.domain.InMemoryRepositoryInterface
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityViewPagerBinding
import com.gmail.pavlovsv93.materialdesign.data.theme.ThemeStorage


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