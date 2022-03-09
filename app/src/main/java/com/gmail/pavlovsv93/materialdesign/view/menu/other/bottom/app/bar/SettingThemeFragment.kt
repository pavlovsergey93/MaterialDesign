package com.gmail.pavlovsv93.materialdesign.view.menu.other.bottom.app.bar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentSettingThemeBinding
import com.gmail.pavlovsv93.materialdesign.model.theme.Theme
import com.gmail.pavlovsv93.materialdesign.model.theme.ThemeStorage
import com.gmail.pavlovsv93.materialdesign.utils.ARG_CLICK_SAVE_THEME
import com.gmail.pavlovsv93.materialdesign.utils.ARG_THEME
import com.gmail.pavlovsv93.materialdesign.utils.DEFAULT_THEME
import com.gmail.pavlovsv93.materialdesign.utils.KEY_CLICK_SAVE_THEME
import com.gmail.pavlovsv93.materialdesign.viewmodel.SettingThemeAdapter

class SettingThemeFragment : Fragment() {

    interface OnClickTheme {
        fun saveClickTheme(theme: Theme)
    }

    companion object {

        fun newInstance(key: Int) = SettingThemeFragment().apply {
            arguments.apply {
                Bundle().apply {
                    putInt(ARG_THEME, key)
                }
            }
        }
    }

    private var _binding: FragmentSettingThemeBinding? = null
    private val binding get() = _binding!!

    private val adapter: SettingThemeAdapter =
        SettingThemeAdapter(object : OnClickTheme {
            override fun saveClickTheme(theme: Theme) {
                Toast.makeText(requireContext(), theme.nameTheme, Toast.LENGTH_SHORT).show()
                val data: Bundle = Bundle().apply {
                    putInt(ARG_CLICK_SAVE_THEME, theme.key)
                }
                parentFragmentManager.setFragmentResult(KEY_CLICK_SAVE_THEME, data)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingThemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARG_THEME).let {
            if (it != null) {
                adapter.getKeyThemeSaved(it)
            }
        }
        val recyclerView: RecyclerView = binding.fSettingThemeContainer
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

    }
}