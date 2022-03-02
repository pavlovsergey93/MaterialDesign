package com.gmail.pavlovsv93.materialdesign.view.menu.navigation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentChipsBinding
import com.gmail.pavlovsv93.materialdesign.utils.*
import com.gmail.pavlovsv93.materialdesign.viewmodel.AppState
import com.gmail.pavlovsv93.materialdesign.viewmodel.PictureViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChipsFragment : Fragment() {

    companion object {
        fun newInstance() = ChipsFragment()
    }

    private var _binding: FragmentChipsBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBottomSheet()

        initViewModel()

        if (savedInstanceState == null) {
            viewModel.sendServerRequestToDate(date = getDate(TODAY))
        }

        binding.fChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                CHIP_3 -> {
                    viewModel.sendServerRequestToDate(date = getDate(DAY_BEFORE_YESTERDAY))
                }
                CHIP_2 -> {
                    viewModel.sendServerRequestToDate(date = getDate(YESTERDAY))
                }
                CHIP_1 -> {
                    viewModel.sendServerRequestToDate(date = getDate(TODAY))
                }
            }
        }
    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.fChipsBottomSheet.fdialogContainer)
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { state ->
            renderData(state)
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun renderData(state: AppState) {
        when (state) {
            is AppState.OnError -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.fChipsImageView.isVisible = false
                binding.fChipsProgress.isVisible = false
                binding.fChipGroup.showSnackBarAction(
                    state.error.toString(),
                    R.string.reload.toString(),
                    {
                        viewModel.sendServerRequestToDate(R.id.f_chips_progress, getDate(TODAY))
                    })
            }
            is AppState.OnLoading -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                binding.fChipsImageView.isVisible = false
                binding.fChipsProgress.isVisible = true
            }
            is AppState.OnSuccess -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                binding.fChipsImageView.isVisible = true
                binding.fChipsProgress.isVisible = false
                if (state.responseData.url.toString().takeLast(4) == ".jpg") {
                    if (state.responseData.hdurl == null) {
                        binding.fChipsImageView.load(state.responseData.url)
                    } else {
                        binding.fChipsImageView.load(state.responseData.hdurl)
                    }
                } else {
                    binding.fChipsImageView.setImageDrawable(R.drawable.ic_outline_image_24.toDrawable())
                    binding.fChipGroup.showSnackBarNoAction(R.string.video_info.toString())
                }
                binding.fChipsBottomSheet.fBottomSheetTitle.text = state.responseData.title
                binding.fChipsBottomSheet.fBottomSheetDescription.text =
                    state.responseData.explanation
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}