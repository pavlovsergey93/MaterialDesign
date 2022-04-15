package com.gmail.pavlovsv93.materialdesign.utils.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ToImageBehavior(context: Context, attr: AttributeSet): CoordinatorLayout.Behavior<View>(context, attr) {

	override fun layoutDependsOn(
		parent: CoordinatorLayout,
		child: View,
		dependency: View
	): Boolean = dependency is AppBarLayout

	override fun onDependentViewChanged(
		parent: CoordinatorLayout,
		child: View,
		dependency: View
	): Boolean {
		val bar = dependency as AppBarLayout
		val barY = bar.y
		val barX = bar.x
		val barW = bar.width
		val barH = bar.height

		return super.onDependentViewChanged(parent, child, dependency)
	}
}