package com.avtograv.weatherapp.ui.main.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.ui.main.MainActivity
import com.avtograv.weatherapp.ui.mainfragment.view.MainFragment
import com.avtograv.weatherapp.ui.managerlocation.view.LocationManagerFragment
import com.avtograv.weatherapp.ui.permissionRequestFragment.PermissionRequestFragment
import com.avtograv.weatherapp.ui.permissionRequestFragment.PermissionRequestType


class ViewPagerAdapter(
    mainActivity: MainActivity,
    private val selectionFragment: Int,
    private val itemCount: Int = 1,
) : FragmentStateAdapter(mainActivity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (selectionFragment) {
            1 -> MainFragment.newInstance(position)
            2 -> LocationManagerFragment()
            3 -> PermissionRequestFragment.newInstance(PermissionRequestType.FINE_LOCATION)
            else -> throw IllegalArgumentException()
        }
    }
}