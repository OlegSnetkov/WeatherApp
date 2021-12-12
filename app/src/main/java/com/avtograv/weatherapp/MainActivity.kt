package com.avtograv.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.presentetion.AddLocationFragment
import com.avtograv.weatherapp.presentetion.mainscreen.view.MainScreenFragment
import com.avtograv.weatherapp.presentetion.viewpager.DepthPageTransformer
import com.avtograv.weatherapp.presentetion.viewpager.ScreenSlidePagerAdapter

class MainActivity : AppCompatActivity(),
    MainScreenFragment.ClickListener,
    AddLocationFragment.BackClickListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        binding.viewpager.adapter = ScreenSlidePagerAdapter(this, SHOW_MAIN_SCREEN)
    }

    override fun letAddLocation() {
        binding.viewpager.adapter = ScreenSlidePagerAdapter(this, SHOW_ADD_LOCATION)
    }

    override fun onBackMainScreen() {
        binding.viewpager.adapter = ScreenSlidePagerAdapter(this, SHOW_MAIN_SCREEN)
    }

    override fun onBackPressed() {
        if (binding.viewpager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.viewpager.currentItem =
                binding.viewpager.currentItem - 1
        }
    }

    companion object{
        const val SHOW_MAIN_SCREEN = 0
        const val SHOW_ADD_LOCATION = 1
    }
}