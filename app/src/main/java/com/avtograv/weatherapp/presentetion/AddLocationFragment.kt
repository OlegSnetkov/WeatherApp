package com.avtograv.weatherapp.presentetion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avtograv.weatherapp.databinding.FragmentAddLocationBinding


class AddLocationFragment : Fragment() {

    private lateinit var binding: FragmentAddLocationBinding
    private var _context: BackClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackClickListener)
            _context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bBack.setOnClickListener {
            _context?.onBackMainScreen()
        }
    }

    override fun onDetach() {
        _context = null
        super.onDetach()
    }

    interface BackClickListener {
        fun onBackMainScreen()
    }
}