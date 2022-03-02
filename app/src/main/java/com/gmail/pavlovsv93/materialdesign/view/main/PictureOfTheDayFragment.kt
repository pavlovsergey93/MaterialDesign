package com.gmail.pavlovsv93.materialdesign.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.gmail.pavlovsv93.materialdesign.utils.*
import com.gmail.pavlovsv93.materialdesign.viewmodel.AppState
import com.gmail.pavlovsv93.materialdesign.viewmodel.PictureViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

//    private var isMain: Boolean = true

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setHasOptionsMenu(true)
//        (requireActivity() as PictureOfTheDayActivity).setSupportActionBar(binding.fBottomAppBar)

        initViewModel()

        binding.fpicturesTextInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(WIKI_URI + binding.fpicturesTextInputEditText.text.toString())
            })
        }

        initBottomSheet()

//        binding.fFab.setOnClickListener {
//            if (isMain) {
//                binding.fBottomAppBar.navigationIcon = null
//                binding.fBottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
//                binding.fFab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_arrow_back_24))
//                binding.fBottomAppBar.replaceMenu(R.menu.menu_other_bottom_app_bar)
//            } else {
//                binding.fBottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_dehaze_24)
//                binding.fBottomAppBar.replaceMenu(R.menu.menu_bottom_app_bar)
//                binding.fFab.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_add_24 ))
//                binding.fBottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
//            }
//            isMain = !isMain
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_bottom_app_bar, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_bab_setting -> {
//                Log.d(TAG_BS, "menu Setting")
//                showFragment(ChipsFragment.newInstance(), BACKSTACK)
//            }
//            R.id.menu_bab_fav -> {
//                Log.d(TAG_BS, "menu Favourite")
//            }
//            android.R.id.home -> {
//                BottomNavigationFragment().show(requireActivity().supportFragmentManager, "")
//                Log.d(TAG_BS, "burger")
//            }
//
//        }
//        return super.onOptionsItemSelected(item)
//    }

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
            BottomSheetBehavior.from(binding.fPicturesBottomSheet.fdialogContainer)
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

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { state ->
            renderData(state)
        })
        viewModel.sendServerRequest(R.id.fpictures_progress)
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.OnError -> {
                binding.fpicturesImageview.isVisible = false
                binding.fpicturesProgress.isVisible = false
                binding.fpicturesProgress.showSnackBarAction(
                    state.error.toString(),
                    R.string.reload.toString(),
                    {
                        viewModel.sendServerRequest(R.id.fpictures_progress)
                    })
            }
            is AppState.OnLoading -> {
                binding.fpicturesImageview.isVisible = false
                binding.fpicturesProgress.isVisible = true
            }
            is AppState.OnSuccess -> {
                binding.fpicturesImageview.isVisible = true
                binding.fpicturesProgress.isVisible = false
                if (state.responseData.url.toString().takeLast(4) == ".jpg") {
                    if (state.responseData.hdurl == null) {
                        binding.fpicturesImageview.load(state.responseData.url)
                    } else {
                        binding.fpicturesImageview.load(state.responseData.hdurl)
                    }
                } else {
                    binding.fpicturesImageview.setImageDrawable(R.drawable.ic_outline_image_24.toDrawable())
                    binding.fpicturesProgress.showSnackBarNoAction(R.string.video_info.toString())
                }
                with(binding.fPicturesBottomSheet) {
                    fBottomSheetTitle.text = state.responseData.title
                    fBottomSheetDescription.text = state.responseData.explanation
                }
            }
        }
    }
}