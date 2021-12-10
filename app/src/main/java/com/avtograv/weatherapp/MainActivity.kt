package com.avtograv.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.databinding.MainActivityBinding
import com.avtograv.weatherapp.presentetion.AddLocationFragment
import com.avtograv.weatherapp.presentetion.MainScreenFragment
import com.avtograv.weatherapp.presentetion.viewpager.AdapterViewPager
import com.avtograv.weatherapp.presentetion.viewpager.DepthPageTransformer

class MainActivity : AppCompatActivity(), MainScreenFragment.ClickListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.setPageTransformer(DepthPageTransformer())
        binding.viewpager.adapter = AdapterViewPager(this, 1)
    }


    override fun goAddLocation() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, AddLocationFragment())
            addToBackStack(null)
            commit()
        }
    }
}