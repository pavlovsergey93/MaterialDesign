package com.gmail.pavlovsv93.materialdesign.ui.animation.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentTrajectoryMovementBinding

class TrajectoryMovementFragment : Fragment() {

	private var _binding: FragmentTrajectoryMovementBinding? = null
	private val binding get() = _binding!!
	private var flag = false

	companion object {
		fun newInstance() = TrajectoryMovementFragment()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentTrajectoryMovementBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.btn.setOnClickListener {
			flag = !flag
			val cb = ChangeBounds()
			cb.duration = 3000
			val am = ArcMotion()
			am.minimumHorizontalAngle = 70F
			am.minimumVerticalAngle = 70F
			cb.setPathMotion(am)
			TransitionManager.beginDelayedTransition(binding.root, cb)
			val params = binding.btn.layoutParams as FrameLayout.LayoutParams
			params.gravity = if (flag) {
				Gravity.BOTTOM or Gravity.END
			} else {
				Gravity.TOP or Gravity.START
			}
			binding.btn.layoutParams = params
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}
