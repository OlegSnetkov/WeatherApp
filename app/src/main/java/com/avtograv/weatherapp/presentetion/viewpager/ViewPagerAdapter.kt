package com.avtograv.weatherapp.presentetion.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.presentetion.locationadd.AddLocationFragment
import com.avtograv.weatherapp.presentetion.main.MainActivity
import com.avtograv.weatherapp.presentetion.mainscreen.view.MainScreenFragment


class ViewPagerAdapter(
    mainActivity: MainActivity,
    private val selectionFragment: Boolean,
    private val itemCount: Int = 1
) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return if (selectionFragment) {
            MainScreenFragment.newInstance(position)
        } else AddLocationFragment()
    }
}