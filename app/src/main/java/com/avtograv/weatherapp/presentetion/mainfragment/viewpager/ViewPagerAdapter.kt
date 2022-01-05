package com.avtograv.weatherapp.presentetion.mainfragment.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.presentetion.managerlocation.view.LocationManagerFragment
import com.avtograv.weatherapp.presentetion.MainActivity
import com.avtograv.weatherapp.presentetion.mainfragment.view.MainFragment


class ViewPagerAdapter(
    mainActivity: MainActivity,
    private val selectionFragment: Boolean,
    private val itemCount: Int
) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return if (selectionFragment) {
            MainFragment.newInstance(position)
        } else LocationManagerFragment()
    }
}