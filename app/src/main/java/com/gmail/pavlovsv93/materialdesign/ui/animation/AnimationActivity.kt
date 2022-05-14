package com.gmail.pavlovsv93.materialdesign.ui.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityAnimationBinding
import com.gmail.pavlovsv93.materialdesign.ui.animation.fragments.AnimationRotateFabFragment
import com.gmail.pavlovsv93.materialdesign.ui.animation.fragments.BoomFragment
import com.gmail.pavlovsv93.materialdesign.ui.animation.fragments.RandomSortFragment
import com.gmail.pavlovsv93.materialdesign.ui.animation.fragments.TrajectoryMovementFragment

class AnimationActivity : AppCompatActivity() {

	private lateinit var binding: ActivityAnimationBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		binding = ActivityAnimationBinding.inflate(layoutInflater)
		setContentView(binding.root)
		super.onCreate(savedInstanceState)

		binding.aaBottomNavigationView.setOnItemSelectedListener {
			when (it.itemId) {
				R.id.boom -> {
					showFragment(BoomFragment.newInstance())
					true
				}
				R.id.trajectory_ovement -> {
					showFragment(TrajectoryMovementFragment.newInstance())
					true
				}
				R.id.sort -> {
					showFragment(RandomSortFragment.newInstance())
					true
				}
				R.id.oblect_animation -> {
					showFragment(AnimationRotateFabFragment.newInstance())
					true
				}
				R.id.constraint->{
					true
				}
				else -> false
			}
		}
		binding.aaBottomNavigationView.selectedItemId = R.id.boom
	}

	private fun showFragment(f: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.aa_fragment_container, f)
			.commit()
	}
}