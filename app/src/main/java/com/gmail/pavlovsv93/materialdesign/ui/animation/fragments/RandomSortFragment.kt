package com.gmail.pavlovsv93.materialdesign.ui.animation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentRandomSortBinding

class RandomSortFragment : Fragment() {
	private var _binding: FragmentRandomSortBinding? = null
	private val binding get() = _binding!!

	companion object {
		fun newInstance() = RandomSortFragment()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentRandomSortBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val title: MutableList<String> = ArrayList()
		repeat(7){
			title.add("item $it")
		}
		binding.btn.setOnClickListener {
			TransitionManager.beginDelayedTransition(binding.containerView)
			title.shuffle()
			binding.containerView.removeAllViews()
			title.forEach {
				binding.containerView.addView(TextView(requireContext()).apply {
					text = it
					ViewCompat.setTransitionName(this, it)
				})
			}
		}
	}
}