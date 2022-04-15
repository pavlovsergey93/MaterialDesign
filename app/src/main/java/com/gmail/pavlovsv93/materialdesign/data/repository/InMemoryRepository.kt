package com.gmail.pavlovsv93.materialdesign.data.repository

import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.domain.InMemoryRepositoryInterface
import com.gmail.pavlovsv93.materialdesign.ui.view.pager.fragment.EarthFragment
import com.gmail.pavlovsv93.materialdesign.ui.view.pager.fragment.MarsFragment
import com.gmail.pavlovsv93.materialdesign.ui.view.pager.fragment.SolarSystemFragment

class InMemoryRepository : InMemoryRepositoryInterface {

    private val fragmentListInMemory = listOf<Fragment>(
        EarthFragment(),
        MarsFragment(),
        SolarSystemFragment()
    )

    override fun getFragmentListInMemory(): List<Fragment> = fragmentListInMemory
}