package com.gmail.pavlovsv93.materialdesign.view.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.BottomNavigationFragmentBinding
import com.gmail.pavlovsv93.materialdesign.view.menu.other.bottom.app.bar.SettingThemeFragment
import com.gmail.pavlovsv93.materialdesign.view.navigation.ViewPager.ViewPagerActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationFragment(private var keyTheme: Int) : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_nav_view_1 -> {
                    showFragment(SettingThemeFragment.newInstance(keyTheme), "SettingThemeFragment")
                }
                R.id.menu_nav_view_2 -> {
                    startActivity(Intent(requireContext(),ViewPagerActivity::class.java))
                }
            }
            dismiss()
            true
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.a_frame_container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}