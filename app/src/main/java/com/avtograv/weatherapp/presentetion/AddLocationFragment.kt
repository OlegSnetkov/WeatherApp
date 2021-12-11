package com.avtograv.weatherapp.presentetion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avtograv.weatherapp.databinding.FragmentAddLocationBinding
import com.avtograv.weatherapp.presentetion.viewpager.ExtensionFragment


class AddLocationFragment : ExtensionFragment() {

    private lateinit var binding: FragmentAddLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddLocationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bBack.setOnClickListener {
            fragmentReplacer.replaceCurrentToDef()
        }
    }
}