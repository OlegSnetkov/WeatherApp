package com.avtograv.weatherapp.presentetion.managerlocation.view

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
import com.avtograv.weatherapp.presentetion.managerlocation.viewmodel.FindLocationFactoryViewModel
import com.avtograv.weatherapp.presentetion.managerlocation.viewmodel.FindLocationViewModelImpl
import com.avtograv.weatherapp.presentetion.managerlocation.viewmodel.LoadingLocationViewState
import com.avtograv.weatherapp.presentetion.managerlocation.viewmodel.LoadingCurrentWeatherViewState


class LocationManagerFragment : Fragment() {

    private lateinit var binding: FragmentAddLocationBinding
    private var _context: BackClickListener? = null
    private lateinit var getLatitude: String
    private lateinit var getLongitude: String
    private lateinit var getLocName: String
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

    private fun getCoordinates(locationName: String) {
        findLocationViewModel.loadCoordinates(locationName)

        findLocationViewModel.stateOutputLoadCoordinates.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoadingLocationViewState.SuccessLoading -> {
                    binding.tvFoundLoc.text = state.dataLatLon.first().locationName
                    getLocName = state.dataLatLon.first().locationName
                    getLatitude = state.dataLatLon.first().latLocation
                    getLongitude = state.dataLatLon.first().lonLocation
                }
                is LoadingLocationViewState.NoLocation -> {}
                is LoadingLocationViewState.FailedLoading -> {
                    Log.e(ContentValues.TAG, "FindLocException", state.exception)
                    Toast.makeText(
                        requireContext(),
                        R.string.error_network_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.exhaustive
        })
    }

    private fun getCurrentWeather(locationName: String) {
        findLocationViewModel.loadCurrentWeather(locationName)
        findLocationViewModel.stateOutputLoadWeather.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoadingCurrentWeatherViewState.SuccessLoading -> {
                    binding.tvAbout.text = state.dataCurrentWeather.aboutWeatherNow
                    binding.tvCurrentTemp.text = state.dataCurrentWeather.tempNow
                   binding.tvFeelsLike.text = state.dataCurrentWeather.feelsLike
                }
                is LoadingCurrentWeatherViewState.NoLocation -> {}
                is LoadingCurrentWeatherViewState.FailedLoading -> {
                    Log.e(ContentValues.TAG, "FindLocException", state.exception)
                    Toast.makeText(
                        requireContext(),
                        R.string.error_network_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.exhaustive
        })
    }

    private fun setupUiComponents() {
        binding.ivArrowLeft.setOnClickListener {
            _context?.onBackMainScreen()
        }

        binding.tvFoundLoc.setOnClickListener {
            val list = getLocationList(requireContext()).toMutableList()
            list.clear()
            list.add(
                DegreesLocation(
                    list.size, getLocName, getLatitude, getLongitude
                )
            )
            saveLocationList(requireContext(), list)
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
                if (s.toString().length > 3) {
                    getCurrentWeather(s.toString())
                    getCoordinates(s.toString())
                }
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