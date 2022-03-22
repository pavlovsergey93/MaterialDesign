package com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepositoryInterface
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityViewPagerBinding


class ViewPagerActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewPagerBinding
    private val repo: InMemoryRepositoryInterface = InMemoryRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.avp_container, ViewPagerFragment.newInstance())
            .commit()
    }
}