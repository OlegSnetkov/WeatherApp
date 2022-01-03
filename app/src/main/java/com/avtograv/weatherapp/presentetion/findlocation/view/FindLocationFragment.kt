package com.avtograv.weatherapp.presentetion.findlocation.view

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avtograv.weatherapp.R
import com.avtograv.weatherapp.common.exhaustive
import com.avtograv.weatherapp.data.locally.getLocationList
import com.avtograv.weatherapp.data.locally.saveLocationList
import com.avtograv.weatherapp.databinding.FragmentAddLocationBinding
import com.avtograv.weatherapp.di.RepositoryProvider
import com.avtograv.weatherapp.model.DegreesLocation
import com.avtograv.weatherapp.presentetion.findlocation.viewmodel.FindLocationFactoryViewModel
import com.avtograv.weatherapp.presentetion.findlocation.viewmodel.FindLocationViewModelImpl
import com.avtograv.weatherapp.presentetion.findlocation.viewmodel.FindLocationViewState


class FindLocationFragment : Fragment() {

    private lateinit var binding: FragmentAddLocationBinding
    private var _context: BackClickListener? = null
    private lateinit var lat: String
    private lateinit var lon: String
    private lateinit var locName: String
    private val findLocationViewModel: FindLocationViewModelImpl by viewModels {
        FindLocationFactoryViewModel(
            (requireActivity() as RepositoryProvider).provideRepository()
        )
    }

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
        setupUiComponents()
    }

    private fun getCoordinates(textInput: String) {
        findLocationViewModel.loadCoordinates(textInput)

        findLocationViewModel.stateOutput.observe(viewLifecycleOwner, { state ->
            when (state) {
                is FindLocationViewState.SuccessLoading -> {
                    binding.tvFoundLoc.text = state.dataLatLon[0].locationName
                    locName = state.dataLatLon[0].locationName
                    lat = state.dataLatLon[0].latLocation
                    lon = state.dataLatLon[0].lonLocation
                }
                is FindLocationViewState.NoLocation -> {}
                is FindLocationViewState.FailedLoading -> {
                    Log.e(ContentValues.TAG, "FindLocException", state.exception)
                    Toast.makeText(
                        requireContext(),
                        R.string.error_network_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.exhaustive
        })

        binding.tvFoundLoc.setOnClickListener {
            val list = getLocationList(requireContext()).toMutableList()
            list.add(
                DegreesLocation(
                    list.size, locName, lat, lon
                )
            )
            saveLocationList(requireContext(), list)
            _context?.onBackMainScreen()
        }
    }

    private fun setupUiComponents() {
        binding.ivArrowLeft.setOnClickListener {
            _context?.onBackMainScreen()
        }

        binding.etNewLoc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
                getCoordinates(binding.etNewLoc.text.toString())
            }
        })
    }

    override fun onDetach() {
        _context = null
        super.onDetach()
    }

    interface BackClickListener {
        fun onBackMainScreen()
    }
}