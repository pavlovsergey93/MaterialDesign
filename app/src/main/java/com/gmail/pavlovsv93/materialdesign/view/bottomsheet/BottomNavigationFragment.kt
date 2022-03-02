package com.gmail.pavlovsv93.materialdesign.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.databinding.BottomNavigationFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}