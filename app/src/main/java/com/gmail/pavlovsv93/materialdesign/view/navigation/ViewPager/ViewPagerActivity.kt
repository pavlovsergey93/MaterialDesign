package com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityViewPagerBinding
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepositoryInterface


class ViewPagerActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewPagerBinding
    private val repo : InMemoryRepositoryInterface = InMemoryRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.aViewPager.adapter = ViewPagerAdapter(supportFragmentManager, repo.getFragmentListInMemory())
    }
}