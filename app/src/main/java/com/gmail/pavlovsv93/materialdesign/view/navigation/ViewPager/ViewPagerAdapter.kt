package com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, fragmentList: List<Fragment>) :
    FragmentStatePagerAdapter(fm) {

//    companion object{
//        private const val EARTH_KEY = 0
//        private const val MARS_KEY = 1
//        private const val SOLAR_SYSTEM_KEY = 2
//    }

    private val fragmentListAdapter = fragmentList

    override fun getCount(): Int = fragmentListAdapter.size

    override fun getItem(position: Int): Fragment = fragmentListAdapter[position]
}