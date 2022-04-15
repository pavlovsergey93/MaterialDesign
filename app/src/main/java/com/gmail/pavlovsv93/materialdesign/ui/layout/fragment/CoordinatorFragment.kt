package com.gmail.pavlovsv93.materialdesign.ui.layout.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentCoordinatorBinding
import com.gmail.pavlovsv93.materialdesign.utils.behavior.ToFABBehavior

class CoordinatorFragment: Fragment() {

    private var _binding :FragmentCoordinatorBinding? = null
    private val binding get() = _binding!!

    companion object{
        fun newInstance() = CoordinatorFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo задать behavior с кода
        val behavior = ToFABBehavior(requireContext())
        (binding.fCoordinatorFab.layoutParams as CoordinatorLayout.LayoutParams).behavior = behavior

    }
}