package com.avtograv.weatherapp.presentetion.viewpager

import android.util.ArrayMap
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.avtograv.weatherapp.MainActivity
import com.avtograv.weatherapp.presentetion.AddLocationFragment
import com.avtograv.weatherapp.presentetion.MainScreenFragment

class AdapterViewPager(mainActivity: MainActivity) :
    FragmentStateAdapter(mainActivity), FragmentReplacer {

    private val mapOfFragment = ArrayMap<Int, ExtensionFragment>()

    override fun createFragment(position: Int): Fragment {
        return mapOfFragment[position] ?: replaceDef(position, false)
    }

    override fun containsItem(itemId: Long): Boolean {
        var isContains = false
        mapOfFragment.values.forEach {
            if (it.pageId == itemId) {
                isContains = true
                return@forEach
            }
        }
        return isContains
    }

    override fun getItemId(position: Int) =
        mapOfFragment[position]?.pageId ?: super.getItemId(position)

    override fun getItemCount(): Int {
        return 2 // TODO
    }

    // region interface FragmentReplacer
    override var lastReplacedPos = 0

    override fun replace(position: Int, newFragment: ExtensionFragment, isNotify: Boolean) {
        newFragment.setPageInfo(
            pagePos = position,
            fragmentReplacer = this
        )

        mapOfFragment[position] = newFragment
        if (isNotify)
            notifyItemChanged(position)
    }

    override fun replaceDef(position: Int, isNotify: Boolean): ExtensionFragment {
        val fragment = when (position) {
            0 -> MainScreenFragment()
            1 -> AddLocationFragment()
            else -> throw IllegalStateException()
        }

        replace(position, fragment, isNotify)
        return fragment
    }

    override fun replaceCurrentToDef() {
        replaceDef(lastReplacedPos)
    }
}