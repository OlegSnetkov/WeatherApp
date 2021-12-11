package com.avtograv.weatherapp.presentetion.viewpager

interface FragmentReplacer {
    fun replace(position: Int, newFragment: ExtensionFragment, isNotify: Boolean = true)
    fun replaceDef(position: Int, isNotify: Boolean = true): ExtensionFragment
    fun replaceCurrentToDef()

    var lastReplacedPos: Int
}