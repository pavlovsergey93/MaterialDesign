package com.gmail.pavlovsv93.materialdesign.ui.animation.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentAnimationRotateFabBinding
import java.util.*

class AnimationRotateFabFragment : Fragment() {
	companion object {
		fun newInstance() = AnimationRotateFabFragment()
	}

	private var _binding: FragmentAnimationRotateFabBinding? = null
	private val binding get() = _binding!!
	private var flag: Boolean = false

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentAnimationRotateFabBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		clickToFabShort()
		binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
			binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
		}
		binding.linearLayoutContainerOne.setOnClickListener {
			Toast.makeText(requireContext(), binding.optionOneTextView.text, Toast.LENGTH_SHORT)
				.show()
		}
		binding.linearLayoutContainerTwo.setOnClickListener {
			Toast.makeText(requireContext(), binding.optionTwoTextView.text, Toast.LENGTH_SHORT)
				.show()
		}

	}

	private fun clickToFabShort() {
		binding.fab.setOnClickListener {
			flag = !flag
			if (flag) {
				objectAnimation(binding.fab, View.ROTATION, 0f, 405f)
				objectAnimation(binding.linearLayoutContainerOne, View.TRANSLATION_Y, 0f, -200f)
				objectAnimation(binding.linearLayoutContainerTwo,View.TRANSLATION_Y,-150f,-350f)
				objectAnimation(binding.frameLayoutBackground, View.ALPHA, 0f, 0.6f)
				animatedView(binding.linearLayoutContainerOne, 1f,true)
				animatedView(binding.linearLayoutContainerTwo, 1f,true)
			} else {
				objectAnimation(binding.fab, View.ROTATION, 405f, 0f)
				objectAnimation(binding.linearLayoutContainerOne, View.TRANSLATION_Y, -200f, 0f)
				objectAnimation(binding.linearLayoutContainerTwo,View.TRANSLATION_Y,-350f,-150f)
				objectAnimation(binding.frameLayoutBackground, View.ALPHA, 0.6f, 0f)
				animatedView(binding.linearLayoutContainerOne, 0f,false)
				animatedView(binding.linearLayoutContainerTwo, 0f,false)
			}
		}
	}

	private fun animatedView(view: View, alpha: Float, isClickable: Boolean) {
		view.animate()
			.alpha(alpha)
			.setDuration(1000L)
			.setListener(object : AnimatorListenerAdapter() {
				override fun onAnimationEnd(animation: Animator?) {
					super.onAnimationEnd(animation)
					view.isClickable = isClickable
				}
			}).start()
	}

	private fun objectAnimation(
		view: View,
		propertyView: Property<View, Float>?,
		start: Float,
		finish: Float
	) {
		ObjectAnimator
			.ofFloat(view, propertyView, start, finish)
			.setDuration(1000L)
			.start()
	}
}