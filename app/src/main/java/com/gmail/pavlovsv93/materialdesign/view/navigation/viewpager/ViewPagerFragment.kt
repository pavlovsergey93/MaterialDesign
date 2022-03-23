package com.gmail.pavlovsv93.materialdesign.view.navigation.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.pavlovsv93.materialdesign.R
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepository
import com.gmail.pavlovsv93.materialdesign.repository.InMemoryRepositoryInterface
import com.gmail.pavlovsv93.materialdesign.databinding.FragmentViewPagerBinding
import com.gmail.pavlovsv93.materialdesign.utils.EARTH_KEY
import com.gmail.pavlovsv93.materialdesign.utils.MARS_KEY
import com.gmail.pavlovsv93.materialdesign.utils.SOLAR_SYSTEM_KEY

class ViewPagerFragment : Fragment() {

    private val repo: InMemoryRepositoryInterface = InMemoryRepository()

    companion object {
        fun newInstance() = ViewPagerFragment()
    }

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            repo.getFragmentListInMemory(),
        )
        binding.fTabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.setPageTransformer(true, ViewPagerTransformer())

        binding.fTabLayout.getTabAt(EARTH_KEY)?.setCustomView(R.layout.activity_view_pager_item_earth)
        binding.fTabLayout.getTabAt(MARS_KEY)?.setCustomView(R.layout.activity_view_pager_item_mars)
        binding.fTabLayout.getTabAt(SOLAR_SYSTEM_KEY)?.setCustomView(R.layout.activity_view_pager_item_solar_system)
    }


}

