package com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(
    fm: FragmentManager,
    fragmentList: List<Fragment>,
) :
    FragmentStatePagerAdapter(fm) {

    private val fragmentListAdapter = fragmentList

    override fun getCount(): Int = fragmentListAdapter.size

    override fun getItem(position: Int): Fragment = fragmentListAdapter[position]

//    override fun getPageTitle(position: Int): CharSequence? {
//
//        return when(position){
//            EARTH_KEY -> "Земля"
//            MARS_KEY -> "Марс"
//            SOLAR_SYSTEM_KEY -> "Солнечная система"
//            else -> ""
//        }
//    }
}