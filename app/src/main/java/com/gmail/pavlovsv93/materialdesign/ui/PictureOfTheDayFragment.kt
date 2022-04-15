package com.gmail.pavlovsv93.materialdesign.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.gmail.pavlovsv93.materialdesign.utils.*
import com.gmail.pavlovsv93.materialdesign.viewmodel.AppState
import com.gmail.pavlovsv93.materialdesign.viewmodel.PictureViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

const val KEY_URL = "KEY_URL"
const val ARG_URL = "ARG_URL"

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() = _binding!!

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        binding.fpicturesTextInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(WIKI_URI + binding.fpicturesTextInputEditText.text.toString())
            })
        }

        initBottomSheet()

        binding.fPicturesChipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chips_3 -> {
                    viewModel.sendServerRequestToDate(date = getDate(DAY_BEFORE_YESTERDAY))
                }
                R.id.chips_2 -> {
                    viewModel.sendServerRequestToDate(date = getDate(YESTERDAY))
                }
                R.id.chips_1 -> {
                    viewModel.sendServerRequestToDate(date = getDate(TODAY))
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment, backstack: Boolean) {
        val sfm = parentFragmentManager.beginTransaction()
            .replace(R.id.a_frame_container, fragment)
        // Проверка необходимости положить предыдущий фрагмент в бэкстэк
        if (backstack) {
            sfm.addToBackStack(fragment.toString())
        }
        sfm.commit()
    }

    private fun initBottomSheet() {
        bottomSheetBehavior =
            BottomSheetBehavior.from(binding.fPicturesBottomSheet.fDialogContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_DRAGGING

        // todo обработка состояния открытия BottomSheet
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // todo обработка состояния "STATE" BottomSheet
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_COLLAPSED $newState")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_DRAGGING $newState")
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_EXPANDED $newState")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_HALF_EXPANDED $newState")
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_HIDDEN $newState")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d(TAG_BS, "onStateChanged: STATE_SETTLING $newState")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //todo возвращает значение, на сколько сейчас BottomSheet открыт
                Log.d(TAG_BS, "onSlide: $slideOffset")
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { state ->
            renderData(state)
        })
        viewModel.sendServerRequestToDate(R.id.fpictures_progress, getDate(TODAY))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun renderData(state: AppState) {
        when (state) {
            is AppState.OnError -> {
                binding.fpicturesImageview.isVisible = false
                binding.fpicturesProgress.isVisible = false
                binding.fpicturesProgress.showSnackBarAction(
                    state.error.toString(),
                    R.string.reload.toString(),
                    {
                        viewModel.sendServerRequestToDate(R.id.fpictures_progress, getDate(TODAY))
                    })
            }
            is AppState.OnLoading -> {
                binding.fmButtonVideo.isVisible = false
                binding.fpicturesImageview.isVisible = false
                binding.fpicturesProgress.isVisible = true
            }
            is AppState.OnSuccess -> {
                binding.fpicturesProgress.isVisible = false

                // if (state.responseData.mediaType != null){ //todo для проверки
                if (state.responseData.mediaType == "image"){
                binding.fpicturesImageview.isVisible = true
                    if (state.responseData.hdurl == null) {
                        binding.fpicturesImageview.load(state.responseData.url)
                    } else {
                        binding.fpicturesImageview.load(state.responseData.hdurl)
                    }
                } else {
                    binding.fmButtonVideo.isVisible = true
                    initShowVideoButton(state.responseData.url)
                }

                with(binding.fPicturesBottomSheet) {
                    fBottomSheetTitle.text = state.responseData.title
                    fBottomSheetDescription.text = state.responseData.explanation
                }
            }
        }
    }
    private fun initShowVideoButton(url : String) {
        val url1 = "https://www.youtube.com/embed/PpyPgJHKxSw?rel=0"
        binding.fmButtonVideo.setOnClickListener {
            parentFragmentManager.setFragmentResult(KEY_URL, Bundle().apply {
                //putString(ARG_URL, url1) //todo для проверки
                putString(ARG_URL, url)
            })
        }
    }

}