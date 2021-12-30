package com.avtograv.weatherapp.presentetion.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.presentetion.locationadd.AddLocationFragment
import com.avtograv.weatherapp.presentetion.MainActivity
import com.avtograv.weatherapp.presentetion.weatherfragment.view.WeatherFragment


class ViewPagerAdapter(
    mainActivity: MainActivity,
    private val selectionFragment: Boolean,
    private val itemCount: Int = 2
) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return if (selectionFragment) {
            WeatherFragment.newInstance(position)
        } else AddLocationFragment()
    }
}