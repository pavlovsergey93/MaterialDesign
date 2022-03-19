package com.gmail.pavlovsv93.materialdesign.repository

import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager.EarthFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager.MarsFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager.SolarSystemFragment

class InMemoryRepository : InMemoryRepositoryInterface {

    private val fragmentListInMemory = listOf<Fragment>(
        EarthFragment(),
        MarsFragment(),
        SolarSystemFragment()
    )

    override fun getFragmentListInMemory(): List<Fragment> = fragmentListInMemory
}