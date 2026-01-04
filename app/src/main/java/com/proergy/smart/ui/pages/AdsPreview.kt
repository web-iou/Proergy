package com.proergy.smart.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.proergy.smart.R
import com.proergy.smart.apis.Http
import kotlinx.coroutines.launch

class AdsPreview : Fragment(R.layout.ads_preview) {
    private lateinit var viewPager: ViewPager2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPager)
//        viewPager.adapter= AdsPagerAdapter()
    }
    private fun loadAds() {
        viewLifecycleOwner.lifecycleScope.launch {
            val response = Http.common.getAds()
            viewPager.adapter=AdsPagerAdapter()
        }
    }
    private inner class AdsPagerAdapter(): FragmentPagerAdapter() {
        override fun getItem(position: Int): Fragment {
            TODO("Not yet implemented")
        }

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

    }
}