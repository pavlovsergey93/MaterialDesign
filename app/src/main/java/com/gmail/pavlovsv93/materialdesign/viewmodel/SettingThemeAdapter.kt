package com.gmail.pavlovsv93.materialdesign.viewmodel

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.view.menu.other.bottom.app.bar.SettingThemeFragment
import com.gmail.pavlovsv93.materialdesign.model.theme.Theme
import com.gmail.pavlovsv93.materialdesign.model.theme.ThemeStorage
import com.gmail.pavlovsv93.materialdesign.utils.DEFAULT_THEME
import com.gmail.pavlovsv93.materialdesign.utils.EquilateralImageView
import com.google.android.material.textview.MaterialTextView

class SettingThemeAdapter(
    private val onClickTheme: SettingThemeFragment.OnClickTheme
) : RecyclerView.Adapter<SettingThemeAdapter.SettingThemeViewHolder>() {

    private var listTheme = Theme.values()
    private var keySaved: Int = DEFAULT_THEME

    fun getKeyThemeSaved(key: Int) {
        keySaved = key
    }


    inner class SettingThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(theme: Theme) {
            val cardView = itemView.findViewById<CardView>(R.id.item_card_theme)
            cardView.setOnClickListener {
                onClickTheme?.saveClickTheme(theme)
            }
            val titleCardView = itemView.findViewById<MaterialTextView>(R.id.item_card_title)
            titleCardView.setText(theme.nameTheme)

            val checkSaveTheme = itemView.findViewById<EquilateralImageView>(R.id.item_card_image)

            if (theme.key == keySaved) {
                checkSaveTheme.isVisible = true
            } else {
                checkSaveTheme.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingThemeViewHolder =
        SettingThemeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent, false) as View
        )

    override fun onBindViewHolder(holder: SettingThemeViewHolder, position: Int) {
        holder.bind(listTheme[position])
    }

    override fun getItemCount(): Int = listTheme.size


}