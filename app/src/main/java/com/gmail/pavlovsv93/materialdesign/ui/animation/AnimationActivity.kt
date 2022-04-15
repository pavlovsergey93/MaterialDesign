package com.gmail.pavlovsv93.materialdesign.ui.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityAnimationBinding
import com.gmail.pavlovsv93.materialdesign.ui.animation.fragments.BoomFragment

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
				else -> false
			}
		}
		binding.aaBottomNavigationView.selectedItemId = R.id.menu_constraint_layout
	}

	private fun showFragment(f: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.aa_fragment_container, f)
			.commit()
	}
}