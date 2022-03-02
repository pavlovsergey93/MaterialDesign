package com.gmail.pavlovsv93.materialdesign.view.menu.navigation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentChipsBinding
import com.gmail.pavlovsv93.materialdesign.utils.*
import com.gmail.pavlovsv93.materialdesign.viewmodel.AppState
import com.gmail.pavlovsv93.materialdesign.viewmodel.PictureViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChipsFragment : Fragment() {

    companion object {
        fun newInstance() = ChipsFragment()
    }

    private var _binding: FragmentChipsBinding? = null
    private val binding get() = _binding!!

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

        initViewModel()

        binding.fChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                CHIP_1 -> {
                    viewModel.sendServerRequestToDate(date = getDate(DAY_BEFORE_YESTERDAY))
                }
                CHIP_2 -> {
                    viewModel.sendServerRequestToDate(date = getDate(YESTERDAY))
                }
                CHIP_3 -> {
                    viewModel.sendServerRequestToDate(date = getDate(TODAY))
                }
            }
        }
        viewModel.sendServerRequest(null)
    }

    private fun initViewModel() {
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { state ->
            renderData(state)
        })
    }

    private fun renderData(state: AppState) {
        when (state){
            is AppState.OnError -> {
                binding.fChipGroup.showSnackBarAction(
                    state.error.toString(),
                    R.string.reload.toString(),
                    {
                        viewModel.sendServerRequest(null)
                    })
            }
            is AppState.OnLoading -> {
                Toast.makeText(context, "Загрузка", Toast.LENGTH_SHORT).show()
            }
            is AppState.OnSuccess -> {
                binding.fChipGroup.showSnackBarNoAction(state.responseData.url)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDate(daysAgo: Int): String {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var date = LocalDate.now()
        var otherDate = LocalDate.parse(date.format(formatter), formatter)
            .minusDays(daysAgo.toLong())
        return otherDate.toString()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}