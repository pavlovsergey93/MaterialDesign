package com.gmail.pavlovsv93.materialdesign.repository

import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.EarthFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.MarsFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager.SolarSystemFragment

class InMemoryRepository : InMemoryRepositoryInterface {

    private val fragmentListInMemory = listOf<Fragment>(
        EarthFragment(),
        MarsFragment(),
        SolarSystemFragment()
    )

    override fun getFragmentListInMemory(): List<Fragment> = fragmentListInMemory
}