package com.avtograv.weatherapp.presentetion.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.MainActivity
import com.avtograv.weatherapp.presentetion.AddLocationFragment
import com.avtograv.weatherapp.presentetion.mainscreen.view.MainScreenFragment

class ScreenSlidePagerAdapter(mainActivity: MainActivity, changeFragment: Int) :
    FragmentStateAdapter(mainActivity) {

    private val _changeFragment = changeFragment

    override fun getItemCount(): Int = 3 // NUM_PAGES

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)
    }

    override fun createFragment(position: Int): Fragment {
        return if (_changeFragment == SHOW_MAIN_SCREEN) {
            MainScreenFragment.newInstance(position)
        } else AddLocationFragment()
    }

    companion object {
        const val SHOW_MAIN_SCREEN = 0
    }
}