package com.avtograv.weatherapp.presentetion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.databinding.FragmentMainScreenBinding
import com.avtograv.weatherapp.presentetion.viewpager.ExtensionFragment


class MainScreenFragment : ExtensionFragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private var _context: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(binding.toolbar)
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_plus_white)
            titleMarginStart = 200
            title = "Location - title"
            setNavigationOnClickListener {
                _context?.goAddLocation()
                // TODO
                val fragmentDeep = AddLocationFragment()
                fragmentReplacer.replace(pagePos, fragmentDeep)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener) {
            _context = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        _context = null
    }

    interface ClickListener {
        fun goAddLocation()
    }
}