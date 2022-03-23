package com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    private var _binding : FragmentEarthBinding? = null
    private val binding get() = _binding!!

    companion object{
        fun newInstance() = EarthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}