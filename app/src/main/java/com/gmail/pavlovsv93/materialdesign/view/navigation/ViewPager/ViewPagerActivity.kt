package com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityViewPagerBinding
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepositoryInterface


class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    private val repo : InMemoryRepositoryInterface = InMemoryRepository()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.aViewPager.adapter = ViewPagerAdapter(supportFragmentManager, repo.getFragmentListInMemory())
    }
}