package com.avtograv.weatherapp.presentetion.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.MainActivity
import com.avtograv.weatherapp.presentetion.MainScreenFragment

class AdapterViewPager(mainActivity: MainActivity, pagerNumber: Int) :
    FragmentStateAdapter(mainActivity) {

    private var numberLocations = pagerNumber

    override fun createFragment(position: Int): Fragment {
        return MainScreenFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return numberLocations
    }
}