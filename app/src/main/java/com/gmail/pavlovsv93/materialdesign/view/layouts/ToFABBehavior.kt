package com.gmail.pavlovsv93.materialdesign.view.layouts

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginEnd
import com.google.android.material.appbar.AppBarLayout
import java.lang.Math.abs

class ToFABBehavior(context: Context, attr: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context, attr) {
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
        val appBarLayout = dependency as AppBarLayout

        val barHeight = appBarLayout.height.toFloat()
        val barWidth = appBarLayout.width.toFloat()
        val barY = appBarLayout.y
        val barX = appBarLayout.x



        if (abs(barY) > (barHeight * 0.67)) {
            child.visibility = View.GONE
            Log.d("ToFABBehavior.if", "Y:$barY  X: $barX")
        } else {
            child.visibility = View.VISIBLE
            child.alpha = (((barHeight * 0.67) - abs(barY)) / (barHeight * 0.67)).toFloat()
            child.x = barWidth * 0.85F - abs(barY)
            Log.d("ToFABBehavior.else", "Y:$barY  X: ${child.x}")
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }
}