package com.avtograv.weatherapp.presentetion.viewpager

import androidx.fragment.app.Fragment
import kotlin.random.Random

abstract class ExtensionFragment : Fragment() {

    val pageId = Random.nextLong(2021, 2021 * 3)
    var pagePos = -1
    lateinit var fragmentReplacer: FragmentReplacer

    fun setPageInfo(pagePos: Int, fragmentReplacer: FragmentReplacer) {
        this.pagePos = pagePos
        this.fragmentReplacer = fragmentReplacer
    }
}