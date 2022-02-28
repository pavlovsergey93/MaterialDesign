package com.gmail.pavlovsv93.materialdesign.view

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.gmail.pavlovsv93.materialdesign.utils.showSnackBarAction
import com.gmail.pavlovsv93.materialdesign.utils.showSnackBarNoAction
import com.gmail.pavlovsv93.materialdesign.viewmodel.AppState
import com.gmail.pavlovsv93.materialdesign.viewmodel.PictureViewModel

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding get() = _binding!!

    private val viewModel: PictureViewModel by lazy {
        ViewModelProvider(this).get(PictureViewModel::class.java)
    }

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
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { state ->
            renderData(state)
        })
        viewModel.sendServerRequest(R.id.fpictures_progress)
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.OnError -> {
                binding.fpicturesLayout.isVisible = false
                binding.fpicturesProgress.isVisible = false
                binding.fpicturesLayout.showSnackBarAction(state.error.toString(), R.string.reload.toString(), {
                    viewModel.sendServerRequest(R.id.fpictures_progress)
                })
            }
            is AppState.OnLoading -> {
                binding.fpicturesLayout.isVisible = false
                binding.fpicturesProgress.isVisible = true
            }
            is AppState.OnSuccess -> {
                binding.fpicturesLayout.isVisible = true
                binding.fpicturesProgress.isVisible = false
                if(state.responseData.url.toString().takeLast(4) == ".jpg") {
                    if (state.responseData.hdurl == null) {
                        binding.fpicturesImageview.load(state.responseData.url)
                    } else {
                        binding.fpicturesImageview.load(state.responseData.hdurl)
                    }
                } else {
                    binding.fpicturesLayout.showSnackBarNoAction(R.string.video_info.toString())
                }
            }
        }

    }
}