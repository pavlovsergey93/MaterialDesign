package com.gmail.pavlovsv93.materialdesign.view.bottomsheet

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.BottomNavigationFragmentBinding
import com.gmail.pavlovsv93.materialdesign.view.menu.other.bottom.app.bar.SettingThemeFragment
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
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.a_frame_container, SettingThemeFragment.newInstance(keyTheme))
                        .commit()
                }
                R.id.menu_nav_view_2 -> {
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                }
            }
            dismiss()
            return@setNavigationItemSelectedListener false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}